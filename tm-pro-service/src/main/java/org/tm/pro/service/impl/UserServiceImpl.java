package org.tm.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tm.pro.entity.User;
import org.tm.pro.entity.UserExample;
import org.tm.pro.mapper.UserMapper;
import org.tm.pro.service.UserService;

import com.github.pagehelper.PageHelper;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public User getById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User getByName(String name) {
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(name);
		PageHelper.startPage(1, 1);
		List<User> users = userMapper.selectByExample(example);
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public int insert(User record) {
		return userMapper.insertSelective(record);
	}
}
