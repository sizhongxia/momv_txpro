package org.tm.pro.service;

import java.util.List;

import org.tm.pro.entity.Authorization;

import com.baomidou.mybatisplus.service.IService;

public interface AuthorizationService extends IService<Authorization> {
	List<Authorization> getAuthorization(boolean isParent);
}
