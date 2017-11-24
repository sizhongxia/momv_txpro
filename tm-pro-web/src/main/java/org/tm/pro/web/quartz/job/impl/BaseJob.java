package org.tm.pro.web.quartz.job.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tm.pro.service.UserService;
import org.tm.pro.utils.TmDateUtil;
import org.tm.pro.web.cache.SystemInfoCacheUtil;
import org.tm.pro.web.quartz.job.RunJob;
import org.tm.pro.web.quartz.model.Job;

@Component
public class BaseJob implements RunJob {

	@Autowired
	UserService userService;

	@Override
	public void run() {
		List<Job> jobs = SystemInfoCacheUtil.jobs;
		if (jobs != null && !jobs.isEmpty()) {
			for (Job job : jobs) {
				if (job.getJobClassName().equals(this.getClass().getName())) {
					job.setUpdateTime(TmDateUtil.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
					break;
				}
			}
		}

		try {
			Thread.sleep(3067);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
