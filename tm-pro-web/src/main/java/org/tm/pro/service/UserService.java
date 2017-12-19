package org.tm.pro.service;

import java.util.List;
import java.util.Map;

import org.tm.pro.entity.User;

public interface UserService {

	User getById(Integer id);

	User getByLoginName(String loginName);

	int getUserCount(Map<String, Object> params);

	List<User> getUserList(Map<String, Object> params, int page, int size);

	int saveUser(User user);

	int deleteUser(User user);

	int updateUser(User user);
}
