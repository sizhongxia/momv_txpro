package org.tm.pro.service;

import java.util.List;

import org.tm.pro.entity.Job;
import org.tm.pro.entity.JobGroup;

public interface JobService {

	Job getById(Integer id);

	Job getByJobId(String jobId);

	JobGroup getByGroupId(Integer groupId);

	List<Job> getAllJobs();

	List<Job> getStartupExecutionJobs();

	int update(Job job);
}
