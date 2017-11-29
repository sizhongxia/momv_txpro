package org.tm.pro.web.quartz.job.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tm.pro.web.quartz.job.RunJob;

import com.tm.pro.redis.util.RedisUtil;
import com.xiaoleilu.hutool.http.HttpUtil;

@Component
public class CheckDomainJob implements RunJob {

	@Autowired
	RedisUtil redisUtil;

	@Override
	public void run() {
		String types[] = new String[] { "com", "cn" };
		String domain = getNext();
		for (String type : types) {
			if (domainIsAvailable(domain, type)) {
				redisUtil.hset("tx:domain:available", domain + "." + type, "available");
			}
		}
	}

	public String getNext() {
		String nextNum = redisUtil.get("tx:domain:next:num");
		if (nextNum != null) {
			Long number = new Long(nextNum);
			redisUtil.set("tx:domain:next:num", (number.longValue() + 1) + "");
			return Long.toString(number.longValue(), 36);
		}
		redisUtil.set("tx:domain:next:num", "1");
		return "0";
	}

	public boolean domainIsAvailable(String domain, String type) {
		return HttpUtil.get("http://panda.www.net.cn/cgi-bin/check.cgi?area_domain=" + domain + "." + type)
				.indexOf("Domain name is available") != -1;
	}

}
