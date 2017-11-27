package org.tm.pro.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tm.pro.anno.DataSource;
import org.tm.pro.entity.Role;
import org.tm.pro.entity.User;
import org.tm.pro.entity.UserExample;
import org.tm.pro.entity.UserExample.Criteria;
import org.tm.pro.entity.UserRole;
import org.tm.pro.entity.UserRoleExample;
import org.tm.pro.mapper.RoleMapper;
import org.tm.pro.mapper.UserMapper;
import org.tm.pro.mapper.UserRoleMapper;
import org.tm.pro.service.RoleService;
import org.tm.pro.service.UserService;
import org.tm.pro.utils.TmMapUtil;
import org.tm.pro.utils.TmStringUtil;

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

	@Override
	@DataSource("slave")
	public User getById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	@DataSource("slave")
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
	@DataSource("slave")
	public long getUserCount(Map<String, Object> params) {
		UserExample example = initParams(params);
		return userMapper.countByExample(example);
	}

	@Override
	@DataSource("slave")
	public List<User> getUserList(Map<String, Object> params, int page, int size) {
		UserExample example = initParams(params);
		example.setOrderByClause("id asc");
		PageHelper.startPage(page, size);
		return userMapper.selectByExample(example);
	}

	private UserExample initParams(Map<String, Object> params) {
		UserExample example = new UserExample();

		Criteria criteria = example.createCriteria();

		String loginName = TmMapUtil.getMapVal(params, "loginName", "");
		if (TmStringUtil.isNotBlank(loginName)) {
			criteria.andLoginNameLike("%" + loginName + "%");
		}
		String userName = TmMapUtil.getMapVal(params, "userName", "");
		if (TmStringUtil.isNotBlank(userName)) {
			criteria.andUsernameLike("%" + userName + "%");
		}
		String phone = TmMapUtil.getMapVal(params, "phone", "");
		if (TmStringUtil.isNotBlank(phone)) {
			criteria.andPhoneLike("%" + phone + "%");
		}
		Integer organizationId = TmMapUtil.getMapVal(params, "organizationId", 0);
		if (organizationId > 0) {
			criteria.andOrganizationIdEqualTo(organizationId);
		}
		return example;
	}

	@Override
	@DataSource("master")
	public int insert(User record) {
		return userMapper.insertSelective(record);
	}

	@Override
	@DataSource("slave")
	public Set<String> getUserAuthorizations(Integer id) {
		Set<String> authorizationCodes = new HashSet<>();
		Set<Role> userRoles = getUserRoles(id);
		if (userRoles != null && !userRoles.isEmpty()) {
			Set<String> _items = null;
			for (Role role : userRoles) {
				_items = roleService.getRoleAuthorizations(role.getId());
				if (_items != null && !_items.isEmpty()) {
					authorizationCodes.addAll(_items);
				}
			}
		}
		return authorizationCodes;
	}

	/**
	 * 
	 */
	@Override
	@DataSource("slave")
	public Set<Role> getUserRoles(Integer id) {
		UserRoleExample example = new UserRoleExample();
		Set<Role> roles = new HashSet<>();

		User user = getById(id);
		if (user == null || user.getOrganizationId() == 0) {
			return roles;
		}
		example.createCriteria().andUserIdEqualTo(id).andOrganizationIdEqualTo(user.getOrganizationId());

		List<UserRole> userRoles = userRoleMapper.selectByExample(example);
		if (userRoles != null && !userRoles.isEmpty()) {
			for (UserRole userRole : userRoles) {
				Role role = roleService.getById(userRole.getRoleId());
				if (role != null && role.getUsingState().equals("Y")) {
					roles.add(role);
				}
			}
		}
		return roles;
	}

	@Override
	@DataSource("master")
	public int update(User user) {
		return userMapper.updateByPrimaryKey(user);
	}

	@Override
	@DataSource("master")
	public int delete(User user) {
		return userMapper.deleteByPrimaryKey(user.getId());
	}

	@Override
	@DataSource("master")
	public int removeRole(Integer userId, Integer roleId) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(userId).andRoleIdEqualTo(roleId);
		return userRoleMapper.deleteByExample(example);
	}

	@Override
	@DataSource("master")
	public int authRole(Integer userId, Integer roleId, Integer organizationId) {
		UserRole record = new UserRole();
		record.setUserId(userId);
		record.setRoleId(roleId);
		record.setOrganizationId(organizationId);
		return userRoleMapper.insert(record);
	}

	@Override
	@DataSource("slave")
	public boolean checkUserRole(Integer userId, Integer roleId, Integer organizationId) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(userId).andRoleIdEqualTo(roleId)
				.andOrganizationIdEqualTo(organizationId);
		return userRoleMapper.countByExample(example) > 0;
	}
}
