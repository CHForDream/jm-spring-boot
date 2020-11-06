package com.game.core.netty;

import java.util.Date;

import com.game.utils.DateUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {
	int lossConnectCount;

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println(DateUtil.getDateStr(new Date()) + "已经" + "秒未收到客户端的消息了！");
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state() == IdleState.WRITER_IDLE) {
				lossConnectCount++;
				if (lossConnectCount > 10) {
					System.out.println("关闭这个不活跃通道！");
					// ctx.channel().close();
				}
			}
		} else {
			super.userEventTriggered(ctx, evt);
		}

	}

}
