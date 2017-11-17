package org.tm.pro.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tm.pro.entity.Role;

public interface RoleService {

	Role getById(Integer id);

	Role getByCode(String code);

	List<Role> getRoleList(Map<String, Object> params);

	Set<String> getRoleAuthorizations(Integer id);

	int insert(Role role);

	int update(Role role);

	int delete(Role role);

	int unAuthorization(Integer roleId, String authorizationCode);
	
	int authorization(Integer roleId, String authorizationCode);

}
