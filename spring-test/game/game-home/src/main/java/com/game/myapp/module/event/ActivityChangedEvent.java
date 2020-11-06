package com.game.myapp.module.event;

import org.springframework.context.ApplicationEvent;

public class ActivityChangedEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	public ActivityChangedEvent(Object source) {
		super(source);
	}
}
