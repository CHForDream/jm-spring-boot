package com.game.core.netty;

import com.game.constants.Loggers;
import com.game.core.netty.platform.Platform;
import com.game.global.Globals;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.ResourceLeakDetector;

/***
 * keytool -genkey -keysize 2048 -validity 365 -keyalg RSA -keypass 123456 -storepass 123456 -keystore fan.jks
 */
public class BattleServer implements INettyServer {
	private int port = 0;

	public BattleServer(int port) {
		this.port = port;
	}

	public static void startNetty() {
		NettyThread thd = new NettyThread(new BattleServer(Globals.getAppConfigBean().getBattleServerPort()));
		thd.setName("battle-server");
		thd.start();
	}

	public void bind() throws Exception {
		Loggers.serverLogger.info("netty start wiht port:" + port);

		EventLoopGroup bossGroup = Platform.isLinux() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
		EventLoopGroup workerGroup = Platform.isLinux() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
		try {
			ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
			ServerBootstrap b = new ServerBootstrap();
			b.channel(Platform.isLinux() ? EpollServerSocketChannel.class : NioServerSocketChannel.class);
			b.group(bossGroup, workerGroup).childHandler(new ChildChannelHandler());

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
			socketChannel.pipeline().addFirst(new LengthFieldBasedFrameDecoder(65535, 0, 2, 2, 2));
			socketChannel.pipeline().addLast(new ProtoBuffDecoder());
			socketChannel.pipeline().addLast(new ProtoBuffEncoder());
			socketChannel.pipeline().addLast(new BattleServerHandler());

//			if (Globals.getAppConfigBean().isEnbaleBattleSsl()) {
//				SSLContext sslContext = SslUtil.createSSLContext(SslUtil.type, SslUtil.path, SslUtil.password); /// SslUtil自定义类
//				SSLEngine sslEngine = sslContext.createSSLEngine();
//				sslEngine.setUseClientMode(false); /// 是否使用客户端模式
//				sslEngine.setNeedClientAuth(false); //// 是否需要验证客户端
//				socketChannel.pipeline().addFirst("ssl", new SslHandler(sslEngine));
//			}
		}
	}
}
