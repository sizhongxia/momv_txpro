package org.tm.pro.web.cache;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.tm.pro.entity.Authorization;
import org.tm.pro.entity.JobGroup;
import org.tm.pro.entity.SystemInfo;
import org.tm.pro.service.AuthorizationService;
import org.tm.pro.service.JobService;
import org.tm.pro.service.SystemInfoService;
import org.tm.pro.web.event.TmApplicationEvent;
import org.tm.pro.web.quartz.model.Job;

public class SystemInfoCacheUtil implements InitializingBean, ApplicationListener<TmApplicationEvent> {

	@Autowired
	SystemInfoService systemInfoService;
	@Autowired
	AuthorizationService authorizationService;
	@Autowired
	JobService jobService;

	// 系统任务
	public static Vector<Job> jobs = new Vector<>();
	// 父级权限字
	public static Vector<Authorization> parentAuthorizations = new Vector<>();
	// 子级权限字
	public static Vector<Authorization> childAuthorizations = new Vector<>();

	public static SystemInfo systemInfo = new SystemInfo();

	public static Authorization getAuthorizationByCode(String authorizationCode) {
		for (Authorization a : childAuthorizations) {
			if (a.getAuthorizationCode().equals(authorizationCode)) {
				return a;
			}
		}
		return null;
	}

	/**
	 * 初始化系统基本信息数据
	 */
	private void initSystemInfo() {
		systemInfo = systemInfoService.getDefaultInfo();
	}

	/**
	 * 初始化系统任务数据
	 */
	private void initSystemJobs() {
		SystemInfoCacheUtil.jobs.clear();
		List<org.tm.pro.entity.Job> _jobs = jobService.getAllJobs();
		if (_jobs != null && !_jobs.isEmpty()) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			CronSequenceGenerator cronGenerator = null;
			for (org.tm.pro.entity.Job job : _jobs) {
				Job jb = new Job();
				jb.setDescription(job.getDescription());
				jb.setJobId(job.getJobId());
				jb.setJobName(job.getJobName());
				JobGroup jobGroup = jobService.getByGroupId(job.getGroupId());
				jb.setJobGroup(jobGroup.getGroupId());
				jb.setJobGroupName(jobGroup.getGroupName());
				jb.setJobStatus(job.getStatus());
				jb.setJobClassName(job.getClazzName());
				jb.setCronExpression(job.getCron());
				cronGenerator = new CronSequenceGenerator(job.getCron());
				jb.setNextExecureTime(df.format(cronGenerator.next(new Date())));
				jb.setConcurrent(job.getIsConcurrent() ? "Y" : "N");
				jb.setStartupExecution(job.getIsStartupExecution().booleanValue() ? "Y" : "N");
				jb.setCreateTime(df.format(new Date(job.getCreateTime())));
				jb.setUpdateTime(df.format(new Date(job.getUpdateTime())));
				SystemInfoCacheUtil.jobs.add(jb);
			}
		}
	}

	private void initAuthorizations() {
		SystemInfoCacheUtil.parentAuthorizations.clear();
		SystemInfoCacheUtil.childAuthorizations.clear();

		List<Authorization> ps = authorizationService.getAuthorization(true);
		if (ps != null && !ps.isEmpty()) {
			for (Authorization a : ps) {
				SystemInfoCacheUtil.parentAuthorizations.add(a);
			}
		}
		List<Authorization> cs = authorizationService.getAuthorization(false);
		if (cs != null && !cs.isEmpty()) {
			for (Authorization a : cs) {
				SystemInfoCacheUtil.childAuthorizations.add(a);
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initSystemInfo();
		// 初始化权限字数据
		initAuthorizations();
		// 初始化系统Job
		initSystemJobs();
	}

	@Override
	public void onApplicationEvent(TmApplicationEvent e) {
		if ("UpdateSystemInfoCacheEvent".equals(e.getSource().toString())) {
			initSystemInfo();
		} else if ("UpdateAuthorizationCacheEvent".equals(e.getSource().toString())) {
			initAuthorizations();
		} else if ("UpdateSystemJobCacheEvent".equals(e.getSource().toString())) {
			initSystemJobs();
		}
	}
}
