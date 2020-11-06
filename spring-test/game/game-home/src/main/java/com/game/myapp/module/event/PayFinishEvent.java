package com.game.myapp.module.event;

import org.springframework.context.ApplicationEvent;

/**
 * 充值完成事件
 * 
 * @author pky
 *
 */
public class PayFinishEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	public PayFinishEvent(Object source) {
		super(source);
	}
}
