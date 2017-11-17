package org.tm.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tm.pro.entity.Authorization;
import org.tm.pro.entity.AuthorizationExample;
import org.tm.pro.mapper.AuthorizationMapper;
import org.tm.pro.service.AuthorizationService;

@Service("authorizationService")
public class AuthorizationServiceImpl implements AuthorizationService {

	@Autowired
	AuthorizationMapper authorizationMapper;

	@Override
	public List<Authorization> getAuthorization(boolean isParent) {
		AuthorizationExample example = new AuthorizationExample();
		if (isParent) {
			example.createCriteria().andPidIsNull();
		} else {
			example.createCriteria().andPidIsNotNull();
		}
		example.setOrderByClause("authorization_code asc");
		return authorizationMapper.selectByExample(example);
	}

	@Override
	public Authorization getAuthorizationByCode(String authorizationCode) {
		AuthorizationExample example = new AuthorizationExample();
		example.createCriteria().andAuthorizationCodeEqualTo(authorizationCode);
		List<Authorization> authorizations = authorizationMapper.selectByExample(example);
		if (authorizations == null || authorizations.isEmpty()) {
			return null;
		}
		return authorizations.get(0);
	}

}
