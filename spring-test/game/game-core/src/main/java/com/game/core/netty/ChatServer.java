package com.game.core.netty;

import com.game.constants.Loggers;
import com.game.core.netty.platform.Platform;
import com.game.global.Globals;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.ResourceLeakDetector;

public class ChatServer implements INettyServer {
	private int port = 0;

	public ChatServer(int port) {
		this.port = port;
	}

	public static void startNetty() {
		NettyThread thd = new NettyThread(new ChatServer(Globals.getAppConfigBean().getChatServerPort()));
		thd.setName("chat-server");
		thd.start();
	}

	public void bind() throws Exception {
		Loggers.serverLogger.info("chat server start wiht port:" + port);

		EventLoopGroup bossGroup = Platform.isLinux() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
		EventLoopGroup workerGroup = Platform.isLinux() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
		try {
			ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
			ServerBootstrap b = new ServerBootstrap();
			b.channel(Platform.isLinux() ? EpollServerSocketChannel.class : NioServerSocketChannel.class);
			b.group(bossGroup, workerGroup).option(ChannelOption.SO_BACKLOG, 128).childHandler(new ChildChannelHandler());

			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
		@Override
		protected void initChannel(SocketChannel socketChannel) throws Exception {
			// 消息包结构: 消息内容字节流长度N(short) + 消息类型(short) + 消息内容字节流(长度为N)
//			LengthFieldBasedFrameDecoder参数
			// 65535, maxFrameLength, 数据帧最大长度
			// 0, lengthFieldOffset, 长度域偏移量，指的是长度域位于整个数据包字节数组中的下标；
			// 2, lengthFieldLength, 长度域的自己的字节数长度。
			// 2, lengthAdjustment, 长度域的偏移量矫正。 如果长度域的值，除了包含有效数据域的长度外，还包含了其他域（如长度域自身）长度，那么，就需要进行矫正。
			//     矫正的值为：包长 - 长度域的值 – 长度域偏移 – 长度域长。
			//     这里把消息类型的2个字节加入解析内容
			// 2, initialBytesToStrip, 接收到的数据包，去除前initialBytesToStrip位
			//     这里去掉了整个数据包的前2个字节(消息内容字节流长度)
			socketChannel.pipeline().addFirst(new LengthFieldBasedFrameDecoder(65535, 0, 2, 2, 2));
			socketChannel.pipeline().addLast(new ProtoBuffDecoder());
			socketChannel.pipeline().addLast(new ProtoBuffEncoder());
			socketChannel.pipeline().addLast(new ChatServerHandler());
		}
	}
}
