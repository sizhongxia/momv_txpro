package org.tm.pro.service;

import java.util.List;
import java.util.Map;

import org.tm.pro.entity.Role;

public interface RoleService {

	Role getById(Integer id);

	Role getByCode(String code);

	List<Role> getRoleList(Map<String, Object> params);

	int saveRole(Role role);

	int updateRole(Role role);

	int deleteRole(Role role);

}
