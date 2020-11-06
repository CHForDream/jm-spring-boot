package com.game.myapp.module.luckdraw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.logf.Logf;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;
import com.game.constants.ErrorCodeConst;
import com.game.core.handler.MsgBack;
import com.game.core.lock.LockManager;
import com.game.db.entity.LuckDrawInfoEntity;
import com.game.db.entity.RoleEntity;
import com.game.generate.bean.GemextractBean;
import com.game.generate.bean.GemluckBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.utils.JsonUtil;

import buffer.CGGetLuckDrawInfoMsg.CGGetLuckDrawInfoProto;
import buffer.CGLuckDrawMsg.CGLuckDrawMsgProto;
import buffer.GCGetLuckDrawInfoMsg;

/**
 * 抽奖
 * 
 * @author LPF
 *
 */
public class LuckDrawManager {

	public static final int LUCK_DRAW_ONE_TYPE = 1;// 单抽
	public static final int LUCK_DRAW_TEN_TYPE = 10;// 十连抽(之前是五连抽)

	public final Random rd = new Random();

	private Map<Integer, AbsExtract> contoller = new HashMap<Integer, AbsExtract>();

	public LuckDrawManager() {
		register();
	}

	void register() {
		contoller.put(LUCK_DRAW_ONE_TYPE, new OneDrawHandler());
		contoller.put(LUCK_DRAW_TEN_TYPE, new TenDrawHandler());
	}

	public void CGGetLuckDrawInfo(MsgBack msgBack, long uid, CGGetLuckDrawInfoProto protoInstance) {
		GCGetLuckDrawInfoMsg.GCGetLuckDrawInfoProto.Builder builder = GCGetLuckDrawInfoMsg.GCGetLuckDrawInfoProto.newBuilder();
		msgBack.addBuilder(builder);

		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		RoleEntity role = null;
		LuckDrawInfoEntity entity = null;
		try {
			role = GameGlobals.roleManager.getRoleEntity(uid);
			if (role == null) {
				builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
				return;
			}

			entity = Globals.getEntityProxy().get(LuckDrawInfoEntity.class, uid);
			if (entity == null) {
				entity = new LuckDrawInfoEntity();
				entity.setLuckNum(0);
				entity.setUid(uid);
				entity.setNextFreeLuckDrawTime(System.currentTimeMillis());
				entity.setOneDrawInfo(GameGlobals.luckDrawManager.initOneDrawInfo());
				Globals.getEntityProxy().insert(entity);
			}
		} finally {
			lock.unlock();
		}
		builder.setLuckNum(entity.getLuckNum());
		builder.setMaxLuckNum(GameGlobals.configManager.getLuckMaxNum());
		builder.setNextFreeTime(entity.getNextFreeLuckDrawTime());
		builder.setCost(packCostInfo());
		builder.setLuckDrawNum(GameGlobals.configManager.getDaiLyLuckDrawNum() - role.getDrawLuckNum());
		builder.setStatus(ErrorCodeConst.SUCCESS);
	}

	private String packCostInfo() {
		String result = "";
		String one = getGemluckBeanByType(LUCK_DRAW_ONE_TYPE).getPrice();
		result += one.split(",")[0] + ",";// 类型,id,数量
		result += one.split(",")[1] + ",";
		result += one.split(",")[2] + ";";
		String five = getGemluckBeanByType(LUCK_DRAW_TEN_TYPE).getPrice();
		result += five.split(",")[0] + ",";// 类型,id,数量
		result += five.split(",")[1] + ",";
		result += five.split(",")[2];
		return result;
	}

	public GemluckBean getGemluckBeanByType(int type) {
		for (GemluckBean temp : Datas.getDataMap(GemluckBean.class).values()) {
			if (temp.getType() == type) {
				return temp;
			}
		}
		return null;
	}

	public String initOneDrawInfo() {
		GemluckBean oneBean = getGemluckBeanByType(LUCK_DRAW_ONE_TYPE);
		int maxNum = oneBean.getRaund();
		int rand = rd.nextInt(maxNum);
		return oneDrawInfoToJson(rand, 0);
	}

	public String oneDrawInfoToJson(int rand, int num) {
		OneDrawInfoVo drawInfoVo = new OneDrawInfoVo();
		drawInfoVo.setFree(rand);
		drawInfoVo.setNum(num);
		return JsonUtil.toJson(drawInfoVo);
	}

	public OneDrawInfoVo getOneDrawInfo(String info) {
		OneDrawInfoVo drawInfoVo = JsonUtil.toObj(info, OneDrawInfoVo.class);
		return drawInfoVo;
	}

	public List<GemextractBean> getDataPool(int type) {
		List<GemextractBean> result = new ArrayList<GemextractBean>();
		for (GemextractBean temp : Datas.getDataMap(GemextractBean.class).values()) {
			if (temp.getGroup() == type) {
				result.add(temp);
			}
		}
		return result;
	}

	public void CGLuckDraw(MsgBack msgBack, long uid, CGLuckDrawMsgProto protoInstance) {
		int type = protoInstance.getType();
		contoller.get(type).extractReward(msgBack, uid);
	}

	public void addLuckDrawLog(long uid, UserBean user, int drawType, int cost, int isFree, int isDouble, String items) {
		try {
//			sb.append(this.drawType + "|");
//			sb.append(this.costNum + "|");
//			sb.append(this.items + "|");
//			sb.append(this.isFree + "|");
//			sb.append(this.isDouble);
			Logf.log(LogfType.LUCK_DRAW, uid, user, drawType + "", cost + "", items, isFree + "", isDouble + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
