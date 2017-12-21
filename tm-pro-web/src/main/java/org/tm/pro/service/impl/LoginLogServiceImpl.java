package org.tm.pro.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.tm.pro.entity.LoginLog;
import org.tm.pro.mapper.LoginLogDao;
import org.tm.pro.service.LoginLogService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 登录日志记录表 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class LoginLogServiceImpl extends ServiceImpl<LoginLogDao, LoginLog> implements LoginLogService {

	@Override
	@DataSource("slave")
	public List<LoginLog> getTopTen(String loginName) {
		LoginLog entity = new LoginLog();
		entity.setLoginName(loginName);
		Wrapper<LoginLog> wrapper = new EntityWrapper<LoginLog>(entity);
		RowBounds rowBounds = new RowBounds(0, 10);
		wrapper.orderBy("login_time", false);
		return baseMapper.selectPage(rowBounds, wrapper);
	}

}
