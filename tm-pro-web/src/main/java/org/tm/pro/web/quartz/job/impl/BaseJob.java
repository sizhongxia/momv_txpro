package org.tm.pro.web.quartz.job.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tm.pro.service.UserService;
import org.tm.pro.web.quartz.job.RunJob;

@Component
public class BaseJob implements RunJob {

	@Autowired
	UserService userService;

	@Override
	public void run() {
		long count = userService.getUserCount(new HashMap<>());
		System.out.println(count);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
