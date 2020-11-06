package com.game.common.client.netty;

import com.game.common.client.Client;
import com.game.common.client.msg.ClientMessageHandler;
import com.game.core.handler.MsgBuilder;
import com.game.core.netty.MessageWrapper;
import com.google.protobuf.AbstractMessage.Builder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@SuppressWarnings("rawtypes")
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
	private Client client;
	private ClientNettyType nettyType;

	public NettyClientHandler(Client client, ClientNettyType nettyType) {
		this.client = client;
		this.nettyType = nettyType;
	}

	public NettyClientHandler() {
	}

	@Override
	public void channelActive(ChannelHandlerContext context) throws Exception {
		switch (nettyType) {
		case BATTLE:
			// 更新上下文
			client.getSocketBattleSession().setContext(context);

			// 回调客户端已连接
			client.onSocketConnect(Client.SOCKET_TYPE_NETTY_BATTLE);
			break;
		case CHAT:
			// 更新上下文
			client.getSocketChatSession().setContext(context);

			// 回调客户端已连接
			client.onSocketConnect(Client.SOCKET_TYPE_NETTY_CHAT);
			break;
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext context, Object msg) throws Exception {
		try {
			MessageWrapper msgWrapper = (MessageWrapper) msg;

			// 处理消息
			MsgBuilder msgBuilder = new MsgBuilder();
			msgBuilder.setMsgType(msgWrapper.getMsgType());
			msgBuilder.setBuilder((Builder) msgWrapper.getMsg().toBuilder());
			ClientMessageHandler.handleMsg(client, msgBuilder);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
	}
}