package com.game.core.netty;

import com.game.common.msg.MessageLogSample;
import com.game.common.player.Player;
import com.game.common.session.GameSession;
import com.game.common.thread.GameThreadPoolManager;
import com.game.common.thread.MessageExecuteRunnable;
import com.game.constants.Loggers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Attribute;

public class BattleServerHandler extends ChannelInboundHandlerAdapter {

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
			player.setServerType(GameSession.NETTY_TYPE_BATTLE);
		}

		// 打印通讯信息
		MessageLogSample.printMessageInfo(msgWrapper.getMsgType(), player.getUid(), msgWrapper.getMsg(), false);

		GameThreadPoolManager.getInstance()
				.battleExecute(new MessageExecuteRunnable(session, msgWrapper.getProcessor(), msgWrapper.getMsgType(), msgWrapper.getMsg()));
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		super.handlerRemoved(ctx);

		Attribute<Player> attribute = ctx.channel().attr(GameSession.USER_DATA);
		Player player = attribute.get();
		if (player == null) {
			return;
		}

		Loggers.serverLogger.info("Context is removed! uid = " + player.getUid());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		/* cause.printStackTrace(); */
		ctx.close();
		Loggers.serverLogger.info(cause.getMessage());
	}
}