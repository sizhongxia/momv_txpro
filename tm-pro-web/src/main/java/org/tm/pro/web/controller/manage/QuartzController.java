package org.tm.pro.web.controller.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tm.pro.model.ApiResultMap;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.controller.base.BaseController;
import org.tm.pro.web.quartz.manager.JobManager;
import org.tm.pro.web.quartz.model.Job;

import com.tm.pro.redis.util.RedisUtil;

@Controller
@RequestMapping(value = "/quartz")
public class QuartzController extends BaseController {

	@Autowired
	JobManager jobManager;
	@Autowired
	RedisUtil redisUtil;

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
	@RequestMapping(value = "/data")
	@RequiresPermissions("auth_job_manager")
	public Map<String, Object> data(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		List<Job> jobs = null;
		try {
			jobs = jobManager.getAllJobs();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		if (jobs == null) {
			jobs = new ArrayList<>();
		}
		data.put("jobs", jobs);
		return data;
	}

	/***
	 * 调度操作
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/option")
	@RequiresPermissions("auth_job_manager")
	public ApiResultMap option(HttpServletRequest request, @RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "jobId", required = true) String jobId,
			@RequestParam(value = "jobGroup", required = true) String jobGroup,
			@RequestParam(value = "jobCron", required = true) String jobCron,
			@RequestParam(value = "isConcurrent", required = true) String isConcurrent,
			@RequestParam(value = "isStartupExecution", required = true) String isStartupExecution) {

		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		if (TmStringUtil.isBlank(type)) {
			arm.setMsg("错误：未传入的类别");
			return arm;
		}
		if (TmStringUtil.isBlank(jobId)) {
			arm.setMsg("错误：未传入的作业类ID");
			return arm;
		}
		if (TmStringUtil.isBlank(jobGroup)) {
			arm.setMsg("错误：未传入的作业类分组");
			return arm;
		}
		if (!"Y".equals(isConcurrent) && !"N".equals(isConcurrent)) {
			arm.setMsg("错误：表单参数有误");
			return arm;
		}
		if (!"Y".equals(isStartupExecution) && !"N".equals(isStartupExecution)) {
			arm.setMsg("错误：表单参数有误");
			return arm;
		}

		Job job = new Job();
		job.setJobId(jobId);
		job.setJobGroup(jobGroup);
		job.setCronExpression(jobCron);
		job.setConcurrent(isConcurrent);
		job.setStartupExecution(isStartupExecution);

		switch (type) {
		case "STOP":
			try {
				if (jobManager.pauseJob(job)) {
					arm.setStatus(true);
					arm.setData(job);
					arm.setMsg("暂停成功");
					return arm;
				}
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
			break;
		case "START":
			try {
				if (jobManager.resumeJob(job)) {
					arm.setStatus(true);
					arm.setData(job);
					arm.setMsg("重启成功");
					return arm;
				}
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
			break;
		case "RESET":
			try {
				if (jobManager.updateJobCron(job)) {
					redisUtil.publish("channel_update_system_cache", "UpdateSystemJobCacheEvent");
					arm.setStatus(true);
					arm.setData(job);
					arm.setMsg("更新成功");
					return arm;
				}
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
			break;
		case "RUN":
			try {
				if (jobManager.triggerJob(job)) {
					arm.setStatus(true);
					arm.setData(job);
					arm.setMsg("执行成功");
					return arm;
				}
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
			break;
		case "REMOVE":
			try {
				if (jobManager.deleteJob(job)) {
					arm.setStatus(true);
					arm.setData(job);
					arm.setMsg("移除成功");
					return arm;
				}
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
			break;
		default:
			arm.setMsg("错误：无效的类别");
			return arm;
		}
		arm.setMsg("错误：操作失败，可能当前服务未开启！");
		return arm;
	}
}
