package org.tm.pro.service;

import org.tm.pro.entity.User;

public interface UserService {

	User getById(Integer id);
	
	User getByName(String name);
	
	int insert(User user);
	
}
