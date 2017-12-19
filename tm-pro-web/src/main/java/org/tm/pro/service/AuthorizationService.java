package org.tm.pro.service;

import java.util.List;

import org.tm.pro.entity.Authorization;

public interface AuthorizationService {

	List<Authorization> getAuthorization(boolean isParent);

	Authorization getAuthorizationByCode(String authorizationCode);

}
