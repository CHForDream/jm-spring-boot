package com.game.robot.model;

import java.util.ArrayList;
import java.util.List;

public class PveCityInfo {
	private int cityId;
	private int pveId;
	private int pveStatus;
	private List<Integer> cityIds = new ArrayList<Integer>();
	private CityInfo cityInfo = new CityInfo();

	public int getCityId() {
		return cityId;
	}

	public int getPveId() {
		return pveId;
	}

	public int getPveStatus() {
		return pveStatus;
	}

	public List<Integer> getCityIds() {
		return cityIds;
	}

	public CityInfo getCityInfo() {
		return cityInfo;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public void setPveId(int pveId) {
		this.pveId = pveId;
	}

	public void setPveStatus(int pveStatus) {
		this.pveStatus = pveStatus;
	}

	public void setCityIds(List<Integer> cityIds) {
		this.cityIds = cityIds;
	}

	public void setCityInfo(CityInfo cityInfo) {
		this.cityInfo = cityInfo;
	}
}
