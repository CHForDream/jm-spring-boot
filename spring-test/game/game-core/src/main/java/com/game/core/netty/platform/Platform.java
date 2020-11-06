/**
 * 
 */
package com.game.core.netty.platform;

/**
 * @author pky
 *
 */
public class Platform {

	private static EPlatform platform = EPlatform.WINDOWS;

	static {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains(EPlatform.WINDOWS.getType())) {
			platform = EPlatform.WINDOWS;
		}
		if (os.contains(EPlatform.LINUX.getType())) {
			platform = EPlatform.LINUX;
		}
	}

	public static boolean isLinux() {
		return Platform.platform == EPlatform.LINUX;
	}

	public static boolean isWindows() {
		return Platform.platform == EPlatform.WINDOWS;
	}
}
