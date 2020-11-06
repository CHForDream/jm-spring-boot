package com.game.myapp.module.hero.com.comment;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import com.game.constants.ErrorCodeConst;
import com.game.core.handler.MsgBack;
import com.game.core.lock.LockManager;
import com.game.db.entity.HeroCommentEntity;
import com.game.db.entity.PetsCommentEntity;
import com.game.db.entity.RoleEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.utils.JsonUtil;

import buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto;
import buffer.CGHeroCommentFabulousMsg.CGHeroCommentFabulousProto;
import buffer.CGHeroCommentGetInfoMsg.CGHeroCommentGetInfoProto;
import buffer.CGHeroCommentMsg.CGHeroCommentProto;
import buffer.GCHeroCommentDelMsg.GCHeroCommentDelProto;
import buffer.GCHeroCommentFabulousMsg.GCHeroCommentFabulousProto;
import buffer.GCHeroCommentFabulousMsg.GCHeroCommentFabulousProto.Builder;
import buffer.GCHeroCommentGetInfoMsg.CommentInfoProto;
import buffer.GCHeroCommentGetInfoMsg.GCHeroCommentGetInfoProto;
import buffer.GCHeroCommentMsg.GCHeroCommentProto;
import redis.clients.jedis.Tuple;

public class HeroCommentService {

	/** 数据库操作 */
	private HeroCommentDao dao = new HeroCommentDao();

	/**
	 * 添加新评论
	 * 
	 * @param msgBack
	 * 
	 * @param uid
	 * @param msg
	 */
	public void CGHeroComment(MsgBack msgBack, long uid, CGHeroCommentProto msg) {
		GCHeroCommentProto.Builder builder = GCHeroCommentProto.newBuilder();
		msgBack.addBuilder(builder);
		int type = msg.getType();
		EComment comment = EComment.valueOf(type);
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			switch (comment) {
			case HERO:
				heroComment(uid, msg.getHeroCode(), msg.getComment(), builder);
				break;
			case PETS:
				petsComment(uid, msg.getHeroCode(), msg.getComment(), builder);
				break;
			}
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 评论英雄
	 * 
	 * @param uid
	 * @param heroCode
	 * @param comment
	 * @param builder
	 * @return
	 */
	private void heroComment(long uid, int heroCode, String comment, GCHeroCommentProto.Builder builder) {
		// 验证是否已经评论过了
		HeroCommentEntity heroCommentEntity = this.dao.findHeroCommentEntityByUidAndHeroCode(uid, heroCode);
		if (heroCommentEntity != null) {
			builder.setStatus(ErrorCodeConst.CANNOT_REPEAT_COMMENT);
			return;
		}

		// 插入数据库
		this.dao.insertHeroComment(uid, heroCode, comment);
		builder.setStatus(ErrorCodeConst.SUCCESS);
		return;
	}

	/***
	 * 评论宠物
	 * 
	 */
	private void petsComment(long uid, int code, String comment, GCHeroCommentProto.Builder builder) {
		// 验证是否已经评论过了
		PetsCommentEntity petsCommentEntity = this.dao.findPetsCommentEntityByUidAndCode(uid, code);
		if (petsCommentEntity != null) {
			builder.setStatus(ErrorCodeConst.CANNOT_REPEAT_COMMENT);
			return;
		}
		// 插入数据库
		this.dao.insertPetsComment(uid, code, comment);
		builder.setStatus(ErrorCodeConst.SUCCESS);
		return;
	}

	/**
	 * 点赞
	 * 
	 * @param msgBack
	 * 
	 * @param uid
	 * @param msg
	 */
	public void CGHeroCommentFabulous(MsgBack msgBack, long uid, CGHeroCommentFabulousProto msg) {
		GCHeroCommentFabulousProto.Builder builder = GCHeroCommentFabulousProto.newBuilder();
		msgBack.addBuilder(builder);
		long commentUid = msg.getCommentUid();
		int heroCode = msg.getHeroCode();
		int type = msg.getType();
		EComment commentType = EComment.valueOf(type);
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			switch (commentType) {
			case HERO:
				heroCommentFabulous(uid, commentType, commentUid, heroCode, builder);
				break;
			case PETS:
				petsCommentFabulous(uid, commentType, commentUid, heroCode, builder);
				break;
			}
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 宠物评论点赞
	 * 
	 * @param commentType
	 * @param commentUid
	 * @param heroCode
	 * @param builder
	 */
	private void petsCommentFabulous(long uid, EComment commentType, long commentUid, int code, Builder builder) {
		ReentrantLock lock = LockManager.getInstance().getLock(commentUid);
		lock.lock();
		try {
			PetsCommentEntity petsCommentEntity = this.dao.findPetsCommentEntityByUidAndCode(commentUid, code);
			if (petsCommentEntity == null) {
				builder.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}
			List<Long> fabulousUidList = GameGlobals.heroManager.getHeroCommentManager().getFabulousUidList(petsCommentEntity.getFabulousUids());
			if (fabulousUidList.contains(uid)) {
				builder.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}

			fabulousUidList.add(uid);
			petsCommentEntity.setFabulousUids(JsonUtil.toJson(fabulousUidList));

			// 宠物点赞之后的操作
			int FabulousNum = petsCommentEntity.getFabulousNum() + 1;
			// 更新mysql
			petsCommentEntity.setFabulousNum(FabulousNum);
			Globals.getEntityProxy().updateAsync(petsCommentEntity);

			// 点赞之后更新redis
			this.dao.afterFabulousUpdateRedis(EComment.PETS, petsCommentEntity.getUid(), petsCommentEntity.getHeroCode(), FabulousNum);
			builder.setStatus(ErrorCodeConst.SUCCESS);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 点赞英雄评论
	 * 
	 * @param commentType
	 * @param commentUid
	 * @param heroCode
	 * @param builder
	 */
	private void heroCommentFabulous(long uid, EComment commentType, long commentUid, int heroCode, Builder builder) {
		ReentrantLock lock = LockManager.getInstance().getLock(commentUid);
		lock.lock();
		try {
			HeroCommentEntity heroCommentEntity = this.dao.findHeroCommentEntityByUidAndHeroCode(commentUid, heroCode);
			if (heroCommentEntity == null) {
				builder.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}
			List<Long> fabulousUidList = GameGlobals.heroManager.getHeroCommentManager().getFabulousUidList(heroCommentEntity.getFabulousUids());
			if (fabulousUidList.contains(uid)) {
				builder.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}
			fabulousUidList.add(uid);
			heroCommentEntity.setFabulousUids(JsonUtil.toJson(fabulousUidList));
			// 英雄点赞之后的操作
			int fabulousNum = heroCommentEntity.getFabulousNum() + 1;
			// 更新mysql
			heroCommentEntity.setFabulousNum(fabulousNum);
			Globals.getEntityProxy().updateAsync(heroCommentEntity);
			// 点赞之后更新redis
			this.dao.afterFabulousUpdateRedis(EComment.HERO, heroCommentEntity.getUid(), heroCommentEntity.getHeroCode(), fabulousNum);
			builder.setStatus(ErrorCodeConst.SUCCESS);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 获取信息
	 * 
	 * @param msgBack
	 * 
	 * @param uid
	 * @param msg
	 * @return
	 */
	public void CGHeroCommentGetInfo(MsgBack msgBack, long uid, CGHeroCommentGetInfoProto msg) {
		GCHeroCommentGetInfoProto.Builder builder = GCHeroCommentGetInfoProto.newBuilder();
		msgBack.addBuilder(builder);
		int rankType = msg.getType();
		int type = msg.getOptType();
		int heroCode = msg.getHeroCode();
		builder.setType(type);
		EHeroCommentType etype = EHeroCommentType.valueOf(EComment.valueOf(type), ECommentRankType.valueOf(rankType));
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			Set<Tuple> rankInfoSet = this.dao.getRankInfoByHeroCodeAndType(heroCode, etype);
			Iterator<Tuple> it = rankInfoSet.iterator();
			switch (EComment.valueOf(type)) {
			case HERO:
				packHeroCommentInfo(uid, it, heroCode, builder);
				break;
			case PETS:
				packPetsCommentInfo(uid, it, heroCode, builder);
				break;
			}
			builder.setStatus(ErrorCodeConst.SUCCESS);
		} finally {
			lock.unlock();
		}
	}

	private void packPetsCommentInfo(long uid, Iterator<Tuple> it, int heroCode, buffer.GCHeroCommentGetInfoMsg.GCHeroCommentGetInfoProto.Builder builder) {
		while (it.hasNext()) {
			Tuple tuple = it.next();
			long commentUid = Long.valueOf(tuple.getElement());
			PetsCommentEntity petsCommentEntity = this.dao.findPetsCommentEntityByUidAndCode(commentUid, heroCode);
			if (petsCommentEntity == null) {
				continue;
			}
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(commentUid);
			if (role == null) {
				continue;
			}

			CommentInfoProto.Builder info = CommentInfoProto.newBuilder();
			info.setHeader(role.getAvatar());
			info.setName(role.getName());
			info.setFabulousStatus(GameGlobals.heroManager.getHeroCommentManager().getFabulousUidList(petsCommentEntity.getFabulousUids()).contains(uid));
			info.setComment(petsCommentEntity.getComment());
			info.setCreateTime(petsCommentEntity.getCreateTime());
			info.setFabulousNum(petsCommentEntity.getFabulousNum());
			info.setUid(commentUid);
			builder.addCommentInfos(info);
		}
	}

	private void packHeroCommentInfo(long uid, Iterator<Tuple> it, int heroCode, buffer.GCHeroCommentGetInfoMsg.GCHeroCommentGetInfoProto.Builder builder) {
		while (it.hasNext()) {
			Tuple tuple = it.next();
			long commentUid = Long.valueOf(tuple.getElement());
			HeroCommentEntity commentEntity = this.dao.findHeroCommentEntityByUidAndHeroCode(commentUid, heroCode);
			if (commentEntity == null) {
				continue;
			}
			CommentInfoProto.Builder info = CommentInfoProto.newBuilder();
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(commentUid);
			if (role == null) {
				continue;
			}
			info.setHeader(role.getAvatar());
			info.setName(role.getName());
			info.setFabulousStatus(GameGlobals.heroManager.getHeroCommentManager().getFabulousUidList(commentEntity.getFabulousUids()).contains(uid));
			info.setComment(commentEntity.getComment());
			info.setCreateTime(commentEntity.getCreateTime());
			info.setFabulousNum(commentEntity.getFabulousNum());
			info.setUid(commentUid);
			builder.addCommentInfos(info);
		}
	}

	/**
	 * 删除无效数据
	 */
	public void delInvalidComment() {
		this.dao.delInvalidComment();
	}

	/**
	 * 删除自己的评论
	 * 
	 * @param msgBack
	 * 
	 * @param uid
	 * @param msg
	 */
	public void CGHeroCommentDel(MsgBack msgBack, long uid, CGHeroCommentDelProto msg) {
		GCHeroCommentDelProto.Builder builder = GCHeroCommentDelProto.newBuilder();
		msgBack.addBuilder(builder);

		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
			return;
		}

		int type = msg.getType();
		EComment comment = EComment.valueOf(type);
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			switch (comment) {
			case HERO:
				this.heroCommentDel(comment, uid, msg.getHeroCode(), builder);
				break;
			case PETS:
				this.petsCommentDel(comment, uid, msg.getHeroCode(), builder);
				break;
			default:
				break;
			}
		} finally {
			lock.unlock();
		}
	}

	private void petsCommentDel(EComment comment, long uid, int heroCode, GCHeroCommentDelProto.Builder builder) {
		PetsCommentEntity petsCommentEntity = this.dao.findPetsCommentEntityByUidAndCode(uid, heroCode);
		if (petsCommentEntity == null) {
			builder.setStatus(ErrorCodeConst.ERROR_PARAM);
			return;
		}

		this.dao.delPetsCommentEntity(petsCommentEntity);
		this.dao.delRedisCommentByCommentType(comment, uid, heroCode);

		builder.setStatus(ErrorCodeConst.SUCCESS);
		return;
	}

	private void heroCommentDel(EComment comment, long uid, int heroCode, GCHeroCommentDelProto.Builder builder) {
		HeroCommentEntity heroCommentEntity = this.dao.findHeroCommentEntityByUidAndHeroCode(uid, heroCode);
		if (heroCommentEntity == null) {
			builder.setStatus(ErrorCodeConst.ERROR_PARAM);
			return;
		}

		this.dao.delHeroCommentEntity(heroCommentEntity);
		this.dao.delRedisCommentByCommentType(comment, uid, heroCode);

		builder.setStatus(ErrorCodeConst.SUCCESS);
	}

	/**
	 * 
	 * @param uid
	 * @param type
	 * @param code
	 */
	public void gmDelComment(long uid, int type, int commentId) {
		EComment comment = EComment.valueOf(type);
		switch (comment) {
		case HERO:
			HeroCommentEntity heroCommentEntity = this.dao.findHeroCommentEntityByUidAndHeroCode(uid, commentId);
			this.dao.delHeroCommentEntity(heroCommentEntity);
			this.dao.delRedisCommentByCommentType(comment, uid, commentId);
		case PETS:
			PetsCommentEntity petsCommentEntity = this.dao.findPetsCommentEntityByUidAndCode(uid, commentId);
			this.dao.delPetsCommentEntity(petsCommentEntity);
			this.dao.delRedisCommentByCommentType(comment, uid, commentId);
		default:
			break;
		}
	}
}
