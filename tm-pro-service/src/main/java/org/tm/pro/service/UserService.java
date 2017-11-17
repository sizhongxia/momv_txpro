package org.tm.pro.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tm.pro.entity.Role;
import org.tm.pro.entity.User;

public interface UserService {

	User getById(Integer id);

	User getByLoginName(String loginName);

	long getUserCount(Map<String, Object> params);

	List<User> getUserList(Map<String, Object> params, int page, int size);

	int insert(User user);

	int delete(User user);

	Set<String> getUserAuthorizations(Integer id);

	Set<Role> getUserRoles(Integer id);

	int update(User user);

	int removeRole(Integer userId, Integer roleId);

	int authRole(Integer userId, Integer roleId, Integer organizationId);

	boolean checkUserRole(Integer userId, Integer roleId, Integer organizationId);
}
