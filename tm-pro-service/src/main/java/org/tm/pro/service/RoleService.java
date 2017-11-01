package org.tm.pro.service;

import java.util.Set;

import org.tm.pro.entity.Role;

public interface RoleService {

	Role getById(Integer id);
	
	Set<String>  getRoleAuthorizations(Integer id);
	
}
