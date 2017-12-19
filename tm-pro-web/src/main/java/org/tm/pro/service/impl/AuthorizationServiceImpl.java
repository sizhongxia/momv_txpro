package org.tm.pro.service.impl;

import org.tm.pro.entity.Authorization;
import org.tm.pro.mapper.AuthorizationDao;
import org.tm.pro.service.AuthorizationService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限字 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
public class AuthorizationServiceImpl extends ServiceImpl<AuthorizationDao, Authorization>
		implements AuthorizationService {

	@Override
	public List<Authorization> getAuthorization(boolean isParent) {
		Wrapper<Authorization> wrapper = new EntityWrapper<Authorization>();
		wrapper.isNull("pid");
		return baseMapper.selectList(wrapper);
	}

	@Override
	public Authorization getAuthorizationByCode(String authorizationCode) {
		Authorization entity = new Authorization();
		entity.setAuthorizationCode(authorizationCode);
		return baseMapper.selectOne(entity);
	}

}
