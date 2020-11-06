package org.leecode;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {
	Selector sel;

	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.bind(new InetSocketAddress(9000));
		Selector sel = Selector.open();
		socketChannel.register(sel, SelectionKey.OP_ACCEPT);

		while (true) {
			System.out.println("等待事件发生");
			// 轮询监听
			sel.select();
		}
	}

	public void initChannel(String hostname, int port) throws IOException {
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		this.sel = Selector.open();
		channel.connect(new InetSocketAddress(hostname, port));
		channel.register(sel, SelectionKey.OP_CONNECT);
	}

	public void connect() throws IOException {
		while (true) {
			sel.select();

			Iterator<SelectionKey> it = this.sel.selectedKeys().iterator();
			while (it.hasNext()) {
				SelectionKey key = it.next();
				if (key.isConnectable()) {
					SocketChannel channel = (SocketChannel) key.channel();
					if (channel.isConnectionPending())
						channel.finishConnect();
					channel.configureBlocking(false);
					ByteBuffer buffer = ByteBuffer.wrap("helloserver".getBytes());
					channel.write(buffer);
					channel.register(sel, SelectionKey.OP_READ);
				} else if (key.isReadable()) {
					SocketChannel channel = (SocketChannel) key.channel();
					channel.configureBlocking(false);
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					channel.read(buffer);
				}
			}
		}
	}
}
