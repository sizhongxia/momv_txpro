package org.tm.pro.web.quartz.job.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tm.pro.service.UserService;
import org.tm.pro.utils.TmDateUtil;
import org.tm.pro.web.cache.SystemInfoCacheUtil;
import org.tm.pro.web.quartz.job.RunJob;
import org.tm.pro.web.quartz.model.Job;

import com.tm.pro.redis.util.RedisUtil;

@Component
public class BaseJob implements RunJob {

	@Autowired
	UserService userService;
	@Autowired
	RedisUtil redisUtil;

	@Override
	public void run() {
		String info = null, clazz = null, key = null;
		List<Job> jobs = SystemInfoCacheUtil.jobs;
		if (jobs != null && !jobs.isEmpty()) {
			for (Job job : jobs) {
				if (job.getJobClassName().equals(this.getClass().getName())) {
					clazz = job.getJobClassName();
					job.setUpdateTime(TmDateUtil.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
					break;
				}
			}
		}
		key = "system:job:runlog:" + clazz;
		info = redisUtil.get(key);
		if (info == null) {
			info = "执行时间：" + System.currentTimeMillis();
		} else {
			info += "<br/>执行时间：" + System.currentTimeMillis();
		}
		if (info.length() > 1024) {
			info = info.substring(info.length() - 1025, info.length() - 1);
		}
		redisUtil.set(key, info);
		try {
			Thread.sleep(3067);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
