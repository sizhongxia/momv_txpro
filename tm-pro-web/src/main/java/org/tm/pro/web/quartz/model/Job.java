package org.tm.pro.web.quartz.model;

/**
 * 任务
 */
public final class Job implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String jobId; // 任务ID
	private String jobGroup; // 任务组id
	private String jobStatus; // 任务状态
	private String cronExpression; // 任务调度时间表达式
	private String jobClassName; // 调度任务（作业）类名
	private boolean isConcurrent; // 是否允许并发执行同一个任务

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public boolean isConcurrent() {
		return isConcurrent;
	}

	public void setConcurrent(boolean isConcurrent) {
		this.isConcurrent = isConcurrent;
	}

}