/**
 * 
 */
package com.game.core.netty.platform;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pky
 *
 */
public enum EPlatform {
	LINUX("linux"),
	WINDOWS("windows"),

	;

	private static Map<String, EPlatform> values = new HashMap<String, EPlatform>();
	static {
		for (EPlatform v : EPlatform.values()) {
			values.put(v.getType(), v);
		}
	}

	private final String type;

	public String getType() {
		return type;
	}

	private EPlatform(String type) {
		this.type = type;
	}

}
