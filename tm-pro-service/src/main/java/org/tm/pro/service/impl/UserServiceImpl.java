package org.tm.pro.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tm.pro.entity.Role;
import org.tm.pro.entity.User;
import org.tm.pro.entity.UserAuthorization;
import org.tm.pro.entity.UserAuthorizationExample;
import org.tm.pro.entity.UserExample;
import org.tm.pro.entity.UserRole;
import org.tm.pro.entity.UserRoleExample;
import org.tm.pro.mapper.RoleMapper;
import org.tm.pro.mapper.UserAuthorizationMapper;
import org.tm.pro.mapper.UserMapper;
import org.tm.pro.mapper.UserRoleMapper;
import org.tm.pro.service.RoleService;
import org.tm.pro.service.UserService;

import com.github.pagehelper.PageHelper;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	RoleService roleService;
	@Autowired
	UserRoleMapper userRoleMapper;
	@Autowired
	RoleMapper roleMapper;
	@Autowired
	UserAuthorizationMapper userAuthorizationMapper;

	@Override
	public User getById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User getByLoginName(String loginName) {
		UserExample example = new UserExample();
		example.createCriteria().andLoginNameEqualTo(loginName);
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

	@Override
	public Set<String> getUserAuthorizations(Integer id) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(id);
		List<UserRole> userRoles = userRoleMapper.selectByExample(example);

		Set<String> primessionWords = new HashSet<>();

		if (userRoles != null && !userRoles.isEmpty()) {
			Set<String> _items = null;
			for (UserRole userRole : userRoles) {
				_items = roleService.getRoleAuthorizations(userRole.getRoleId());
				if (_items != null && !_items.isEmpty()) {
					primessionWords.addAll(_items);
				}
			}
		}

		UserAuthorizationExample example2 = new UserAuthorizationExample();
		example2.createCriteria().andUserIdEqualTo(id);
		List<UserAuthorization> authorizations = userAuthorizationMapper.selectByExample(example2);
		if (authorizations != null && !authorizations.isEmpty()) {
			for (UserAuthorization userAuthorization : authorizations) {
				primessionWords.add(userAuthorization.getPremissionWord());
			}
		}

		return primessionWords;
	}

	@Override
	public Set<String> getUserRoles(Integer id) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(id);
		List<UserRole> userRoles = userRoleMapper.selectByExample(example);
		Set<String> roles = new HashSet<>();
		if (userRoles != null && !userRoles.isEmpty()) {
			for (UserRole userRole : userRoles) {
				Role role = roleService.getById(userRole.getRoleId());
				if (role != null) {
					roles.add(role.getRoleCode());
				}
			}
		}
		return roles;
	}

	@Override
	public void update(User user) {
		userMapper.updateByPrimaryKey(user);
	}
}
