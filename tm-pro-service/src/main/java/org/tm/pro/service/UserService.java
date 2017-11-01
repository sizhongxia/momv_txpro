package org.tm.pro.service;

import java.util.Set;

import org.tm.pro.entity.User;

public interface UserService {

	User getById(Integer id);

	User getByLoginName(String loginName);

	int insert(User user);

	Set<String> getUserAuthorizations(Integer id);

	Set<String> getUserRoles(Integer id);

	void update(User user);

}
