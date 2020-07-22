package org.shell.test;

import org.shell.test.shell.ShellUtils;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		String cmd = "";
		if (args.length == 1) {
			cmd = args[0];
		}
		ShellUtils.runShell(cmd);
	}
}
