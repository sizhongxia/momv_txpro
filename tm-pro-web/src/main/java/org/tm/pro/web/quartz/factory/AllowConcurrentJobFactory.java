package org.tm.pro.web.quartz.factory;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.quartz.manager.JobManager;
import org.tm.pro.web.quartz.model.Job;
import org.tm.pro.web.utils.FastJsonUtil;

public class AllowConcurrentJobFactory extends  AbstractJobFactory implements org.quartz.Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String scheduleJob = (String) context.getMergedJobDataMap().get(JobManager.SCHEDULER_NAME);
		if (!TmStringUtil.isBlank(scheduleJob)) {
			Job job = FastJsonUtil.jsonToObj(scheduleJob, Job.class);
			invoke(job);
		}
	}

}
