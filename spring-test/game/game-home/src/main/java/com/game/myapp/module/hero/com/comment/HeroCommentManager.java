package com.game.myapp.module.hero.com.comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.game.core.handler.MsgBack;
import com.game.utils.JsonUtil;

import buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto;
import buffer.CGHeroCommentFabulousMsg.CGHeroCommentFabulousProto;
import buffer.CGHeroCommentGetInfoMsg.CGHeroCommentGetInfoProto;
import buffer.CGHeroCommentMsg.CGHeroCommentProto;

public class HeroCommentManager {

	/** 协议处理类 */
	private HeroCommentService service = new HeroCommentService();

	/**
	 * 添加新评论
	 * 
	 * @param msgBack
	 * 
	 * @param uid
	 * @param msg
	 * @return
	 */
	public void CGHeroComment(MsgBack msgBack, long uid, CGHeroCommentProto msg) {
		this.service.CGHeroComment(msgBack, uid, msg);
	}

	/***
	 * 点赞
	 * 
	 * @param msgBack
	 * 
	 * @param uid
	 * @param msg
	 * @return
	 */
	public void CGHeroCommentFabulous(MsgBack msgBack, long uid, CGHeroCommentFabulousProto msg) {
		this.service.CGHeroCommentFabulous(msgBack, uid, msg);
	}

	/**
	 * 获取评论信息
	 * 
	 * @param msgBack
	 * 
	 * @param uid
	 * @param msg
	 */
	public void CGHeroCommentGetInfo(MsgBack msgBack, long uid, CGHeroCommentGetInfoProto msg) {
		this.service.CGHeroCommentGetInfo(msgBack, uid, msg);
	}

	public List<Long> getFabulousUidList(String uids) {
		if (uids == null || Objects.equals("", uids)) {
			return new ArrayList<Long>();
		}
		return JsonUtil.toList(uids, Long.class);
	}

	/***
	 * 定期删除
	 */
	public void delInvalidComment() {
		this.service.delInvalidComment();
	}

	/**
	 * 删除
	 * 
	 * @param msgBack
	 * 
	 * @param uid
	 * @param msg
	 */
	public void CGHeroCommentDel(MsgBack msgBack, long uid, CGHeroCommentDelProto msg) {
		this.service.CGHeroCommentDel(msgBack, uid, msg);
	}

	/**
	 * GM删除
	 * 
	 * @param uid
	 * @param type
	 * @param commentId
	 */
	public void gmDelComment(long uid, int type, int commentId) {
		this.service.gmDelComment(uid, type, commentId);
	}
}
