package org.tm.pro.service;

import java.util.List;

import org.tm.pro.entity.Job;

public interface JobService {

	Job getById(Integer id);

	Job getByJobId(String jobId);

	List<Job> getAllJobs();

	List<Job> getStartupExecutionJobs();

	int update(Job job);
}
