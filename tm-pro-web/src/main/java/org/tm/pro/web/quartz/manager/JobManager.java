package org.tm.pro.web.quartz.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.quartz.factory.AllowConcurrentJobFactory;
import org.tm.pro.web.quartz.factory.DisallowConcurrentJobFactory;
import org.tm.pro.web.quartz.model.Job;
import org.tm.pro.web.utils.FastJsonUtil;

/**
 * 任务调度管理器， 实现任务的动态操作
 */
public class JobManager {

	// 调度名称
	public static final String SCHEDULER_NAME = "scheduler";
	// 为调度管理器注入工厂bean
	private StdScheduler scheduler;

	public void setScheduler(StdScheduler scheduler) {
		this.scheduler = scheduler;
	}

	// 添加任务
	public void addJob(Job job) throws SchedulerException {
		if (job == null)
			return;
		if (TmStringUtil.isBlank(job.getJobId()))
			return;
		if (TmStringUtil.isBlank(job.getJobGroup()))
			return;
		if (TmStringUtil.isBlank(job.getCronExpression()))
			return;
		if (TmStringUtil.isBlank(job.getJobClassName()))
			return;
		addCronJob(job);
	}

	// 添加 cron 表达式任务
	private void addCronJob(Job job) throws SchedulerException {
		// 根据任务id和任务组Id创建触发器key
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobId(), job.getJobGroup());
		// 获取触发器对象
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		// 不存在，创建一个
		if (null == trigger) {
			JobDetail jobDetail = JobBuilder
					.newJob(job.isConcurrent() ? AllowConcurrentJobFactory.class : DisallowConcurrentJobFactory.class)
					.withIdentity(job.getJobId(), job.getJobGroup()).build();
			jobDetail.getJobDataMap().put(SCHEDULER_NAME, FastJsonUtil.objToJson(job));
			trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
					.withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression())).build();
			scheduler.scheduleJob(jobDetail, trigger);
		}
	}

	/**
	 * 获取所有计划中的任务列表
	 * 
	 * @return
	 * @throws SchedulerException
	 **/
	public List<Job> getAllJob() throws SchedulerException {
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<Job> jobList = new ArrayList<>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				Job job = new Job();
				job.setJobId(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}

	/**
	 * 所有正在运行的job
	 *
	 * @return
	 * @throws SchedulerException
	 */
	public List<Job> getRunningJob() throws SchedulerException {
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<Job> jobList = new ArrayList<>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			Job job = new Job();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			job.setJobId(jobKey.getName());
			job.setJobGroup(jobKey.getGroup());
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			job.setJobStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCronExpression(cronExpression);
			}
			jobList.add(job);
		}
		return jobList;
	}

	// 更新调度任务的调度时间
	public void updateJobCron(Job job) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobId(), job.getJobGroup());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
				.withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression())).build();
		scheduler.rescheduleJob(triggerKey, trigger);
	}

	// 暂停一个调度任务
	public void pauseJob(Job scheduleJob) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobId(), scheduleJob.getJobGroup());
		scheduler.pauseJob(jobKey);
	}

	// 恢复一个调度任务
	public void resumeJob(Job job) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(job.getJobId(), job.getJobGroup());
		scheduler.resumeJob(jobKey);
	}

	// 删除一个调度任务
	public void deleteJob(Job scheduleJob) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobId(), scheduleJob.getJobGroup());
		scheduler.deleteJob(jobKey);
	}

	// 立即执行默个调度任务
	public void triggerJob(Job job) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(job.getJobId(), job.getJobGroup());
		scheduler.triggerJob(jobKey);
	}

	// 获取Job状态
	public String getJobStatus(Job job) throws SchedulerException {
		TriggerState state = scheduler.getTriggerState(new TriggerKey(job.getJobId(), job.getJobGroup()));
		return state.name();
	}

}
