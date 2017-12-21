package org.tm.pro.web.sms;

import org.springframework.context.ApplicationEvent;

public class SendSmsEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public SendSmsEvent(SendSmsEventObj source) {
		super(source);
	}

}
