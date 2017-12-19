package org.tm.pro.service;

import java.util.List;

import org.tm.pro.entity.LoginLog;

public interface LoginLogService {

	int saveLoginLog(LoginLog loginLog);

	List<LoginLog> getTopTen(String loginName);
}
