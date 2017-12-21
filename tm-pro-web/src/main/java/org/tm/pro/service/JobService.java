package org.tm.pro.service;

import java.util.List;

import org.tm.pro.entity.Job;

import com.baomidou.mybatisplus.service.IService;

public interface JobService extends IService<Job> {
	List<Job> getStartupExecutionJobs();
}
