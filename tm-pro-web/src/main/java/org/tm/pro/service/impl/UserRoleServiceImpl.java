package org.tm.pro.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tm.pro.entity.Role;
import org.tm.pro.entity.UserRole;
import org.tm.pro.mapper.UserRoleDao;
import org.tm.pro.service.RoleAuthorizationService;
import org.tm.pro.service.RoleService;
import org.tm.pro.service.UserRoleService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 用户角色记录表 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

	@Autowired
	RoleService roleService;
	@Autowired
	RoleAuthorizationService authorizationService;

	@Override
	@DataSource("slave")
	public Set<String> getUserAuthorizations(Integer userId) {
		Set<Role> roles = getUserRoles(userId);
		if (roles == null || roles.isEmpty()) {
			return null;
		}
		Set<String> authorizations = new HashSet<>();
		for (Role role : roles) {
			authorizations.addAll(authorizationService.getRoleAuthorizations(role.getId()));
		}
		return authorizations;
	}

	@Override
	@DataSource("slave")
	public Set<Role> getUserRoles(Integer userId) {
		UserRole entity = new UserRole();
		entity.setUserId(userId);
		Wrapper<UserRole> wrapper = new EntityWrapper<UserRole>(entity);
		List<UserRole> userRoles = baseMapper.selectList(wrapper);
		if (userRoles == null || userRoles.isEmpty()) {
			return null;
		}
		Set<Role> roles = new HashSet<>();
		for (UserRole userRole : userRoles) {
			Role role = roleService.selectById(userRole.getRoleId());
			if (role != null && "Y".equals(role.getUsingState())) {
				roles.add(role);
			}
		}
		return roles;
	}

	@Override
	@DataSource("slave")
	public boolean checkUserRole(Integer userId, Integer roleId, Integer organizationId) {
		UserRole entity = new UserRole();
		entity.setUserId(userId);
		entity.setRoleId(roleId);
		entity.setOrganizationId(organizationId);
		return baseMapper.selectOne(entity) != null;
	}
}
