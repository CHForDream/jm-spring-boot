package com.game.core.netty;

public class NettyThread extends Thread {
	INettyServer server = null;

	public NettyThread(INettyServer server) {
		this.server = server;
	}

	@Override
	public void run() {
		try {
			server.bind();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
