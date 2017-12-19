package org.tm.pro.service.impl;

import org.tm.pro.entity.SystemInfo;
import org.tm.pro.mapper.SystemInfoDao;
import org.tm.pro.service.SystemInfoService;
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
public class SystemInfoServiceImpl extends ServiceImpl<SystemInfoDao, SystemInfo> implements SystemInfoService {

	@Override
	public SystemInfo getDefaultInfo() {
		return baseMapper.selectOne(new SystemInfo());
	}

	@Override
	public int updateSystemInfo(SystemInfo systemInfo) {
		return baseMapper.updateById(systemInfo);
	}

}
