package com.game.robot.model;

import java.util.ArrayList;
import java.util.List;

public class CityInfo {
	private int cityId;
	private List<DupInfo> dupInfo = new ArrayList<DupInfo>();

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public List<DupInfo> getDupInfo() {
		return dupInfo;
	}

	public void setDupInfo(List<DupInfo> dupInfo) {
		this.dupInfo = dupInfo;
	}
}
