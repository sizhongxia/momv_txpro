package org.tm.pro.web.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tm.pro.web.controller.base.BaseController;
import org.tm.pro.web.quartz.manager.JobManager;
import org.tm.pro.web.quartz.model.Job;

@Controller
@RequestMapping(value = "/quartz")
public class QuartzController extends BaseController {

	@Autowired
	JobManager jobManager;

	public QuartzController() {
	}

	/***
	 * 调度服务
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/index")
	public Map<String, Object> index(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();

		Job job = new Job();
		job.setJobId("jobId");
		job.setJobGroup("groupId");
		job.setJobClassName("org.tm.pro.web.quartz.job.impl.BaseJob");
		job.setCronExpression("0/1 * * * * ?");
		job.setConcurrent(false);
		try {
			jobManager.addJob(job);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		try {
			data.put("status", jobManager.getJobStatus(job));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return data;
	}

	/***
	 * 调度服务
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/runing")
	public Map<String, Object> runing(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();

		try {
			List<Job> runJobs = jobManager.getRunningJob();
			if (runJobs != null && !runJobs.isEmpty()) {
				for (Job j : runJobs) {
					System.out.println(j.getJobId());
					System.out.println(j.getJobStatus());
					System.out.println("—-—-—-—-—-—-—-—-—-—-—-—");
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

		return data;
	}

	/***
	 * 调度服务
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/all")
	public Map<String, Object> all(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		try {
			List<Job> jobs = jobManager.getAllJob();
			if (jobs != null && !jobs.isEmpty()) {
				for (Job j : jobs) {
					System.out.println(j.getJobId());
					System.out.println(j.getJobStatus());
					System.out.println("—·—·—·—·—·—·—·—·—·—·—·—");
					jobManager.resumeJob(j);
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

		return data;
	}

	/***
	 * 调度服务
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/stop")
	public Map<String, Object> stop(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		Job scheduleJob = new Job();
		scheduleJob.setJobId("jobId");
		scheduleJob.setJobGroup("groupId");
		try {
			jobManager.pauseJob(scheduleJob);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return data;
	}

	/***
	 * 调度服务
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/start")
	public Map<String, Object> start(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		Job scheduleJob = new Job();
		scheduleJob.setJobId("jobId");
		scheduleJob.setJobGroup("groupId");
		try {
			jobManager.resumeJob(scheduleJob);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return data;
	}

	/***
	 * 调度服务
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/update")
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		Job scheduleJob = new Job();
		scheduleJob.setJobId("jobId");
		scheduleJob.setJobGroup("groupId");
		scheduleJob.setCronExpression("0/5 * * * * ?");
		try {
			jobManager.updateJobCron(scheduleJob);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return data;
	}

	/***
	 * 调度服务
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/trigger")
	public Map<String, Object> trigger(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		Job scheduleJob = new Job();
		scheduleJob.setJobId("jobId");
		scheduleJob.setJobGroup("groupId");
		try {
			jobManager.triggerJob(scheduleJob);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return data;
	}

	/***
	 * 调度服务
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		Job scheduleJob = new Job();
		scheduleJob.setJobId("jobId");
		scheduleJob.setJobGroup("groupId");
		try {
			jobManager.deleteJob(scheduleJob);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return data;
	}
}
