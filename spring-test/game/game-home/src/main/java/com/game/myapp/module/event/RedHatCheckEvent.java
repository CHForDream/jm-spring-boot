package com.game.myapp.module.event;

import org.springframework.context.ApplicationEvent;

/**
 * 
 * 红点检测事件
 * 
 * @author lpf
 *
 */
public class RedHatCheckEvent extends ApplicationEvent {
	private static final long serialVersionUID = 5001708841901624670L;

	public RedHatCheckEvent(Object source) {
		super(source);
	}
}
