package org.leecode;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
	public static void main(String[] args) throws IOException {
		ServerSocketChannel socketChannel = ServerSocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.bind(new InetSocketAddress(9000));
		Selector sel = Selector.open();
		socketChannel.register(sel, SelectionKey.OP_ACCEPT);

		while (true) {
			System.out.println("等待事件发生");
			// 轮询监听channel里的key select是阻塞的
			sel.select();
			System.out.println("有事件发生");
			Iterator<SelectionKey> it = sel.selectedKeys().iterator();
			while (it.hasNext()) {
				SelectionKey key = it.next();
				it.remove();
				handle(key);
			}
		}
	}

	private static void handle(SelectionKey key) throws IOException {
		if (key.isAcceptable()) {
			System.out.println("收到客户端的连接");
			ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
			SocketChannel sc = ssc.accept();
			sc.configureBlocking(false);
			sc.register(key.selector(), SelectionKey.OP_READ);
		} else if (key.isReadable()) {
			System.out.println("接收到数据");
			SocketChannel sc = (SocketChannel) key.channel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
//			int len = sc.read(buffer);
			ByteBuffer write = ByteBuffer.wrap("hello".getBytes());
			sc.write(write);
			key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
			sc.close();
		}
	}
}
