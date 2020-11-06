package com.game.myapp.module.guide;

import java.util.ArrayList;
import java.util.List;

public class GuideBeanVo {

	private int type;// 引导类型
	private int id;// 引导id
	private int param;// 打成条件
	private List<Integer> mutexList = new ArrayList<Integer>();// 互斥引导
	private int nextId;// 下个引导id
	private boolean endImmediately;// 是否立即完成引导

	private boolean isServerPrefinish;// 是否可以服务器提前完成
	private int serverConditionType;
	private int serverConditionNum;

	public List<Integer> getMutexList() {
		return mutexList;
	}

	public void setMutexList(List<Integer> mutexList) {
		this.mutexList = mutexList;
	}

	public int getNextId() {
		return nextId;
	}

	public void setNextId(int nextId) {
		this.nextId = nextId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParam() {
		return param;
	}

	public void setParam(int param) {
		this.param = param;
	}

	public void addmutexList(int id) {
		this.mutexList.add(id);
	}

	public boolean isEndImmediately() {
		return endImmediately;
	}

	public void setEndImmediately(boolean endImmediately) {
		this.endImmediately = endImmediately;
	}

	public boolean isServerPrefinish() {
		return isServerPrefinish;
	}

	public void setServerPrefinish(boolean isServerPrefinish) {
		this.isServerPrefinish = isServerPrefinish;
	}

	public int getServerConditionType() {
		return serverConditionType;
	}

	public void setServerConditionType(int serverConditionType) {
		this.serverConditionType = serverConditionType;
	}

	public int getServerConditionNum() {
		return serverConditionNum;
	}

	public void setServerConditionNum(int serverConditionNum) {
		this.serverConditionNum = serverConditionNum;
	}
}
