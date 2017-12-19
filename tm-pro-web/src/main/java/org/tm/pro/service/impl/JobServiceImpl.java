package org.tm.pro.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tm.pro.entity.Job;
import org.tm.pro.mapper.JobDao;
import org.tm.pro.service.JobService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 定时任务 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class JobServiceImpl extends ServiceImpl<JobDao, Job> implements JobService {

	@Override
	@DataSource("slave")
	public Job getById(Integer id) {
		return baseMapper.selectById(id);
	}

	@Override
	@DataSource("slave")
	public Job getByJobId(String jobId) {
		Job entity = new Job();
		entity.setJobId(jobId);
		return baseMapper.selectOne(entity);
	}

	@Override
	@DataSource("slave")
	public List<Job> getAllJobs() {
		Wrapper<Job> wrapper = new EntityWrapper<>();
		return baseMapper.selectList(wrapper);
	}

	@Override
	@DataSource("slave")
	public List<Job> getStartupExecutionJobs() {
		Job job = new Job();
		job.setStatus("NORMAL");
		job.setIsStartupExecution(1);
		Wrapper<Job> wrapper = new EntityWrapper<Job>(job);
		return baseMapper.selectList(wrapper);
	}

	@Override
	public int update(Job job) {
		return baseMapper.updateById(job);
	}

}
