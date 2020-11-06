package com.game.common.client.netty;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.game.common.client.Client;
import com.game.common.client.ClientMessageLogSample;
import com.game.constants.Loggers;
import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.Message;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.ResourceLeakDetector;

public class NettyClientSession {
	private Client client;
	private ChannelHandlerContext context;

	public NettyClientSession(Client client) {
		this.client = client;
	}

	public void connect(ClientNettyType nettyType, String ip, int port) {
		if (context == null) {
			connectToNettyServer(nettyType, ip, port);
			return;
		}

		if (!isConnect()) {
			connectToNettyServer(nettyType, ip, port);
		}
	}

	@SuppressWarnings({ "rawtypes" })
	public void sendMsg(Builder builder) {
		if (builder == null) {
			Loggers.clientLogger.warn("Send message is null!");
			return;
		}

		if (context != null && context.channel().isActive() && context.channel().isWritable()) {
			try {
				// 打印通讯信息
				printMessageInfo(builder);

				context.channel().writeAndFlush(builder);
			} catch (Exception e) {
				e.printStackTrace();
				Loggers.clientLogger.error("socket error", e);
			}
		}
	}

	public void close() {
		if (context != null && context.channel().isActive()) {
			context.close();
		}
	}

	public boolean isConnect() {
		return context != null && context.channel().isActive() && context.channel().isWritable();
	}

	private void connectToNettyServer(ClientNettyType nettyType, String ip, int port) {
		Bootstrap bootstrap = new Bootstrap();
		EventLoopGroup group = new NioEventLoopGroup();
		ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
		try {
			bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true)
					.option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(65535)).handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline().addFirst(new LengthFieldBasedFrameDecoder(65535, 0, 2, 2, 2));
							socketChannel.pipeline().addLast(new ProtoBuffClientDecoder());
							socketChannel.pipeline().addLast(new ProtoBuffClientEncoder());
							socketChannel.pipeline().addLast(new NettyClientHandler(client, nettyType));
						}
					});
			ChannelFuture f = bootstrap.connect(ip, port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}

	@SuppressWarnings("rawtypes")
	private void printMessageInfo(Builder builder) {
		try {
			Method getMsgTypeMethod = builder.getClass().getMethod("getMsgType");
			short msgType = Short.parseShort(getMsgTypeMethod.invoke(builder).toString());
			Message msg = builder.build();
			// TODO uid
			long uid = 0L;
			ClientMessageLogSample.printMessageInfo(msgType, uid, msg, true);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ChannelHandlerContext getContext() {
		return context;
	}

	public void setContext(ChannelHandlerContext context) {
		this.context = context;
	}
}
