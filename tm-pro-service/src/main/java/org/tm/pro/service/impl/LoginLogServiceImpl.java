package org.tm.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tm.pro.anno.DataSource;
import org.tm.pro.entity.LoginLog;
import org.tm.pro.entity.LoginLogExample;
import org.tm.pro.mapper.LoginLogMapper;
import org.tm.pro.service.LoginLogService;

import com.github.pagehelper.PageHelper;

@Service(value = "loginLogService")
public class LoginLogServiceImpl implements LoginLogService {

	@Autowired
	LoginLogMapper loginLogMapper;

	@Override
	@DataSource("master")
	public int insert(LoginLog loginLog) {
		return loginLogMapper.insert(loginLog);
	}

	@Override
	@DataSource("slave")
	public List<LoginLog> getTopTen(String loginName) {
		PageHelper.startPage(1, 10);
		LoginLogExample example = new LoginLogExample();
		example.createCriteria().andLoginNameEqualTo(loginName);
		example.setOrderByClause("login_time desc");
		return loginLogMapper.selectByExample(example);
	}

}
