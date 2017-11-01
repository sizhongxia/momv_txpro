package org.tm.pro.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tm.pro.entity.Role;
import org.tm.pro.entity.RoleAuthorization;
import org.tm.pro.entity.RoleAuthorizationExample;
import org.tm.pro.mapper.RoleAuthorizationMapper;
import org.tm.pro.mapper.RoleMapper;
import org.tm.pro.service.RoleService;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleMapper roleMapper;
	@Autowired
	RoleAuthorizationMapper roleAuthorizationMapper;

	@Override
	public Role getById(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public Set<String> getRoleAuthorizations(Integer id) {
		RoleAuthorizationExample example = new RoleAuthorizationExample();
		example.createCriteria().andRoleIdEqualTo(id);
		List<RoleAuthorization> authorizations = roleAuthorizationMapper.selectByExample(example);
		Set<String> primessionWords = new HashSet<>();
		if (authorizations != null && !authorizations.isEmpty()) {
			for (RoleAuthorization authorization : authorizations) {
				primessionWords.add(authorization.getPremissionWord());
			}
		}
		return primessionWords;
	}

}
