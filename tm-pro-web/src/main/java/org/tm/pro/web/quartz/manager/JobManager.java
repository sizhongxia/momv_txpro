package org.tm.pro.web.quartz.manager;

import java.util.Date;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.tm.pro.entity.JobGroup;
import org.tm.pro.service.JobService;
import org.tm.pro.utils.TmDateUtil;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.cache.SystemInfoCacheUtil;
import org.tm.pro.web.quartz.factory.AllowConcurrentJobFactory;
import org.tm.pro.web.quartz.factory.DisallowConcurrentJobFactory;
import org.tm.pro.web.quartz.model.Job;
import org.tm.pro.web.utils.FastJsonUtil;

/**
 * 任务调度管理器， 实现任务的动态操作
 */
public class JobManager implements InitializingBean {

	@Autowired
	private JobService jobService;

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
					.newJob("Y".equals(job.getConcurrent()) ? AllowConcurrentJobFactory.class
							: DisallowConcurrentJobFactory.class)
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
	public List<Job> getAllJobs() throws SchedulerException {
		List<Job> jobs = SystemInfoCacheUtil.jobs;
		if (jobs == null || jobs.isEmpty()) {
			return null;
		}
		CronSequenceGenerator cronGenerator = null;
		for (Job job : jobs) {
			job.setJobStatus(getJobStatus(job));
			cronGenerator = new CronSequenceGenerator(job.getCronExpression());
			if (job.getJobStatus().equals("NORMAL") || job.getJobStatus().equals("BLOCKED")
					|| job.getJobStatus().equals("COMPLETE")) {
				job.setNextExecureTime(
						TmDateUtil.format(cronGenerator.next(new Date()).getTime(), "yyyy-MM-dd HH:mm:ss"));
			} else {
				job.setNextExecureTime("-");
			}
		}
		return jobs;
	}

	// 更新调度任务的调度时间
	public boolean updateJobCron(Job job) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobId(), job.getJobGroup());
		if (scheduler.checkExists(triggerKey)) {
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
					.withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression())).build();
			scheduler.rescheduleJob(triggerKey, trigger);
			org.tm.pro.entity.Job _job = jobService.getByJobId(job.getJobId());
			_job.setCron(job.getCronExpression());
			if (jobService.update(_job) > 0) {
				return true;
			}
		}
		return false;
	}

	// 暂停一个调度任务
	public boolean pauseJob(Job job) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(job.getJobId(), job.getJobGroup());
		if (scheduler.checkExists(jobKey)) {
			scheduler.pauseJob(jobKey);
			return true;
		}
		return false;
	}

	// 恢复一个调度任务
	public boolean resumeJob(Job job) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(job.getJobId(), job.getJobGroup());
		if (scheduler.checkExists(jobKey)) {
			scheduler.resumeJob(jobKey);
		} else {
			if (TmStringUtil.isBlank(job.getCronExpression())) {
				return false;
			}
			org.tm.pro.entity.Job _job = jobService.getByJobId(job.getJobId());
			job.setJobClassName(_job.getClazzName());
			addJob(job);
		}
		return true;
	}

	// 删除一个调度任务
	public boolean deleteJob(Job scheduleJob) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobId(), scheduleJob.getJobGroup());
		if (scheduler.checkExists(jobKey)) {
			scheduler.deleteJob(jobKey);
			return true;
		}
		return false;
	}

	// 立即执行默个调度任务
	public boolean triggerJob(Job job) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(job.getJobId(), job.getJobGroup());
		if (scheduler.checkExists(jobKey)) {
			scheduler.triggerJob(jobKey);
			return true;
		}
		return false;
	}

	// 获取Job状态
	public String getJobStatus(Job job) throws SchedulerException {
		TriggerKey triggerKey = new TriggerKey(job.getJobId(), job.getJobGroup());
		if (scheduler.checkExists(triggerKey)) {
			TriggerState state = scheduler.getTriggerState(triggerKey);
			return state.name();
		}
		return TriggerState.NONE.name();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (jobService != null) {
			List<org.tm.pro.entity.Job> jobs = jobService.getStartupExecutionJobs();
			if (jobs != null && !jobs.isEmpty()) {
				for (org.tm.pro.entity.Job job : jobs) {
					Job jb = new Job();
					jb.setJobId(job.getJobId());
					JobGroup jobGroup = jobService.getByGroupId(job.getGroupId());
					jb.setJobGroup(jobGroup.getGroupId());
					jb.setJobClassName(job.getClazzName());
					jb.setCronExpression(job.getCron());
					jb.setConcurrent(job.getIsConcurrent().booleanValue() ? "Y" : "N");
					addJob(jb);
				}
			}
		}
	}

}
