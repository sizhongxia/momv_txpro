package org.tm.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tm.pro.entity.Job;
import org.tm.pro.entity.JobExample;
import org.tm.pro.entity.JobGroup;
import org.tm.pro.mapper.JobGroupMapper;
import org.tm.pro.mapper.JobMapper;
import org.tm.pro.service.JobService;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	JobMapper jobMapper;
	@Autowired
	JobGroupMapper jobGroupMapper;

	@Override
	public Job getById(Integer id) {
		return jobMapper.selectByPrimaryKey(id);
	}

	@Override
	public Job getByJobId(String jobId) {
		JobExample example = new JobExample();
		example.createCriteria().andJobIdEqualTo(jobId);
		List<Job> jobs = jobMapper.selectByExample(example);
		if (jobs != null && !jobs.isEmpty()) {
			return jobs.get(0);
		}
		return null;
	}

	@Override
	public JobGroup getByGroupId(Integer groupId) {
		return jobGroupMapper.selectByPrimaryKey(groupId);
	}

	@Override
	public List<Job> getAllJobs() {
		return jobMapper.selectByExample(new JobExample());
	}

	@Override
	public List<Job> getStartupExecutionJobs() {
		JobExample example = new JobExample();
		example.createCriteria().andIsStartupExecutionEqualTo(Boolean.TRUE).andStatusNotEqualTo("NONE");
		return jobMapper.selectByExample(example);
	}

	@Override
	public int update(Job job) {
		return jobMapper.updateByPrimaryKey(job);
	}

}
