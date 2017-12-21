package org.tm.pro.service;

import java.util.List;

import org.tm.pro.entity.LoginLog;

import com.baomidou.mybatisplus.service.IService;

public interface LoginLogService  extends IService<LoginLog>{
	List<LoginLog> getTopTen(String loginName);
}
