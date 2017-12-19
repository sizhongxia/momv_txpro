package org.tm.pro.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.tm.pro.entity.RoleAuthorization;
import org.tm.pro.mapper.RoleAuthorizationDao;
import org.tm.pro.service.RoleAuthorizationService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 角色授权记录表 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
public class RoleAuthorizationServiceImpl extends ServiceImpl<RoleAuthorizationDao, RoleAuthorization>
		implements RoleAuthorizationService {

	@Override
	public Set<String> getRoleAuthorizations(Integer roleId) {
		RoleAuthorization entity = new RoleAuthorization();
		entity.setRoleId(roleId);
		Wrapper<RoleAuthorization> wrapper = new EntityWrapper<RoleAuthorization>(entity);
		List<RoleAuthorization> authorizations = baseMapper.selectList(wrapper);
		if (authorizations == null || authorizations.isEmpty()) {
			return null;
		}
		Set<String> auths = new HashSet<>();
		for (RoleAuthorization authorization : authorizations) {
			auths.add(authorization.getAuthorizationCode());
		}
		return auths;
	}

	@Override
	public int unAuthorization(Integer roleId, String authorizationCode) {
		RoleAuthorization entity = new RoleAuthorization();
		entity.setRoleId(roleId);
		entity.setAuthorizationCode(authorizationCode);
		Wrapper<RoleAuthorization> wrapper = new EntityWrapper<RoleAuthorization>(entity);
		return baseMapper.delete(wrapper);
	}

	@Override
	public int authorization(Integer roleId, String authorizationCode) {
		RoleAuthorization entity = new RoleAuthorization();
		entity.setRoleId(roleId);
		entity.setAuthorizationCode(authorizationCode);
		return baseMapper.insert(entity);
	}
}
