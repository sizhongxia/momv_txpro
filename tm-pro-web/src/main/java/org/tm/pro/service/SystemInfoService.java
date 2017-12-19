package org.tm.pro.service;

import org.tm.pro.entity.SystemInfo;

public interface SystemInfoService {

	SystemInfo getDefaultInfo();

	int updateSystemInfo(SystemInfo systemInfo);

}
