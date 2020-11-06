package com.game.myapp.module.event;

import org.springframework.context.ApplicationEvent;

public class LogoutEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	public LogoutEvent(Object source) {
		super(source);
	}
}
