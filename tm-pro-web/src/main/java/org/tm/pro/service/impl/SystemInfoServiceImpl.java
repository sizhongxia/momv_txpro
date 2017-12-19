package org.tm.pro.service.impl;

import org.tm.pro.entity.SystemInfo;
import org.tm.pro.mapper.SystemInfoDao;
import org.tm.pro.service.SystemInfoService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统信息表 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class SystemInfoServiceImpl extends ServiceImpl<SystemInfoDao, SystemInfo> implements SystemInfoService {

	@Override
	@DataSource("slave")
	public SystemInfo getDefaultInfo() {
		return baseMapper.selectOne(new SystemInfo());
	}

	@Override
	public int updateSystemInfo(SystemInfo systemInfo) {
		return baseMapper.updateById(systemInfo);
	}

}
