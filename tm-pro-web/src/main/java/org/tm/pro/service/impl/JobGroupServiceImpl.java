package org.tm.pro.service.impl;

import org.tm.pro.entity.JobGroup;
import org.tm.pro.mapper.JobGroupDao;
import org.tm.pro.service.JobGroupService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务组 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
public class JobGroupServiceImpl extends ServiceImpl<JobGroupDao, JobGroup> implements JobGroupService {

	@Override
	public JobGroup getById(Integer id) {
		return baseMapper.selectById(id);
	}
}
