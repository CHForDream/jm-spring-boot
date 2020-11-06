package com.game.core.netty;

import com.game.common.msg.MessageLogSample;
import com.game.common.player.Player;
import com.game.common.session.GameSession;
import com.game.common.thread.GameThreadPoolManager;
import com.game.common.thread.MessageExecuteRunnable;
import com.game.global.Globals;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Attribute;

public class ChatServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);

//		GameSession session = new GameSession();
//		session.setConnection(ctx);
//		session.setUid(ctx.hashCode());
//		session.setLastHeartBeatMill(System.currentTimeMillis());
//		session.setNetty_type(GameSession.NETTY_TYPE_CHAT);
//		Globals.getChatSessionManager().addSession(session);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		MessageWrapper msgWrapper = (MessageWrapper) msg;

		Attribute<Player> attribute = ctx.channel().attr(GameSession.USER_DATA);
		Player player = attribute.get();
		GameSession session = null;
		if (player != null) {
			session = player.getSession();
			player.setLastHeartBeatMill(System.currentTimeMillis());
		} else {
			session = new GameSession(ctx);
			player = session.getPlayer();
			player.setServerType(GameSession.NETTY_TYPE_CHAT);
		}

		// 打印通讯信息
		MessageLogSample.printMessageInfo(msgWrapper.getMsgType(), player.getUid(), msgWrapper.getMsg(), false);

		GameThreadPoolManager.getInstance()
				.chatExecute(new MessageExecuteRunnable(session, msgWrapper.getProcessor(), msgWrapper.getMsgType(), msgWrapper.getMsg()));
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		super.handlerRemoved(ctx);

		Attribute<Player> attribute = ctx.channel().attr(GameSession.USER_DATA);
		Player player = attribute.get();
		if (player == null) {
			return;
		}

		long uid = player.getUid();
		Globals.getChatSessionManager().off(uid);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		cause.printStackTrace();
//		Loggers.serverLogger.warn(cause.getMessage());
		ctx.close();
	}
}