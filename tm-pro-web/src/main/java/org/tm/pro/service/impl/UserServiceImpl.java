package org.tm.pro.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.tm.pro.entity.User;
import org.tm.pro.mapper.UserDao;
import org.tm.pro.service.UserService;
import org.tm.pro.utils.TmMapUtil;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

	@Override
	@DataSource("slave")
	public User getById(Integer id) {
		return baseMapper.selectById(id);
	}

	@Override
	@DataSource("slave")
	public User getByLoginName(String loginName) {
		User entity = new User();
		entity.setLoginName(loginName);
		return baseMapper.selectOne(entity);
	}

	@Override
	@DataSource("slave")
	public int getUserCount(Map<String, Object> params) {
		Wrapper<User> wrapper = new EntityWrapper<>();
		String loginName = TmMapUtil.getMapVal(params, "loginName", "");
		if (TmStringUtil.isNotBlank(loginName)) {
			wrapper.like("login_name", loginName);
		}
		String userName = TmMapUtil.getMapVal(params, "userName", "");
		if (TmStringUtil.isNotBlank(userName)) {
			wrapper.like("user_name", userName);
		}
		String phone = TmMapUtil.getMapVal(params, "phone", "");
		if (TmStringUtil.isNotBlank(phone)) {
			wrapper.like("phone", phone);
		}
		Integer organizationId = TmMapUtil.getMapVal(params, "organizationId", 0);
		if (organizationId > 0) {
			wrapper.eq("organization_id", organizationId);
		}
		return baseMapper.selectCount(wrapper);
	}

	@Override
	@DataSource("slave")
	public List<User> getUserList(Map<String, Object> params, int page, int size) {
		RowBounds rowBounds = new RowBounds((page - 1) * size, size);
		Wrapper<User> wrapper = new EntityWrapper<>();
		String loginName = TmMapUtil.getMapVal(params, "loginName", "");
		if (TmStringUtil.isNotBlank(loginName)) {
			wrapper.like("login_name", loginName);
		}
		String userName = TmMapUtil.getMapVal(params, "userName", "");
		if (TmStringUtil.isNotBlank(userName)) {
			wrapper.like("user_name", userName);
		}
		String phone = TmMapUtil.getMapVal(params, "phone", "");
		if (TmStringUtil.isNotBlank(phone)) {
			wrapper.like("phone", phone);
		}
		Integer organizationId = TmMapUtil.getMapVal(params, "organizationId", 0);
		if (organizationId > 0) {
			wrapper.eq("organization_id", organizationId);
		}
		return baseMapper.selectPage(rowBounds, wrapper);
	}

	@Override
	public int saveUser(User user) {
		return baseMapper.insert(user);
	}

	@Override
	public int deleteUser(User user) {
		return baseMapper.deleteById(user.getId());
	}


	@Override
	public int updateUser(User user) {
		return baseMapper.updateById(user);
	}

}
