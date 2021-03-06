package org.tm.pro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tm.pro.entity.SystemInfo;
import org.tm.pro.mapper.SystemInfoMapper;
import org.tm.pro.service.SystemInfoService;

@Service(value = "systemInfoService")
public class SystemInfoServiceImpl implements SystemInfoService {

	@Autowired
	SystemInfoMapper systemInfoMapper;

	@Override
	public SystemInfo getDefaultInfo() {
		return systemInfoMapper.selectByPrimaryKey(1);
	}

	@Override
	public int updateSystemInfo(SystemInfo systemInfo) {
		return systemInfoMapper.updateByPrimaryKey(systemInfo);
	}

}
