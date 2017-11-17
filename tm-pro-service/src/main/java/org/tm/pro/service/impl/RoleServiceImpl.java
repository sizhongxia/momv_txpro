package org.tm.pro.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tm.pro.entity.Role;
import org.tm.pro.entity.RoleAuthorization;
import org.tm.pro.entity.RoleAuthorizationExample;
import org.tm.pro.entity.RoleExample;
import org.tm.pro.entity.RoleExample.Criteria;
import org.tm.pro.mapper.RoleAuthorizationMapper;
import org.tm.pro.mapper.RoleMapper;
import org.tm.pro.service.RoleService;
import org.tm.pro.utils.TmMapUtil;
import org.tm.pro.utils.TmStringUtil;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleMapper roleMapper;
	@Autowired
	RoleAuthorizationMapper roleAuthorizationMapper;

	@Override
	public Role getById(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public Role getByCode(String code) {
		RoleExample example = new RoleExample();
		example.createCriteria().andRoleCodeEqualTo(code);
		List<Role> roles = roleMapper.selectByExample(example);
		if (roles != null && !roles.isEmpty()) {
			return roles.get(0);
		}
		return null;
	}

	@Override
	public List<Role> getRoleList(Map<String, Object> params) {
		RoleExample example = new RoleExample();

		Criteria criteria = example.createCriteria();
		Integer organizationId = TmMapUtil.getMapVal(params, "organizationId", 0);
		if (organizationId > 0) {
			criteria.andOrganizationIdEqualTo(organizationId);
		}

		String roleName = TmMapUtil.getMapVal(params, "roleName", "");
		if (TmStringUtil.isNotBlank(roleName)) {
			criteria.andRoleNameLike("%" + roleName + "%");
		}

		String roleCode = TmMapUtil.getMapVal(params, "roleCode", "");
		if (TmStringUtil.isNotBlank(roleCode)) {
			criteria.andRoleCodeLike("%" + roleCode + "%");
		}

		String usingState = TmMapUtil.getMapVal(params, "usingState", "");
		if (TmStringUtil.isNotBlank(usingState)) {
			criteria.andUsingStateEqualTo(usingState);
		}

		return roleMapper.selectByExample(example);
	}

	@Override
	public Set<String> getRoleAuthorizations(Integer id) {
		RoleAuthorizationExample example = new RoleAuthorizationExample();
		example.createCriteria().andRoleIdEqualTo(id);
		List<RoleAuthorization> authorizations = roleAuthorizationMapper.selectByExample(example);
		Set<String> authorizationCodes = new HashSet<>();
		if (authorizations != null && !authorizations.isEmpty()) {
			for (RoleAuthorization authorization : authorizations) {
				authorizationCodes.add(authorization.getAuthorizationCode());
			}
		}
		return authorizationCodes;
	}

	@Override
	public int insert(Role role) {
		return roleMapper.insert(role);
	}

	@Override
	public int update(Role role) {
		return roleMapper.updateByPrimaryKey(role);
	}

	@Override
	public int delete(Role role) {
		return roleMapper.deleteByPrimaryKey(role.getId());
	}

	@Override
	public int unAuthorization(Integer roleId, String authorizationCode) {
		RoleAuthorizationExample example = new RoleAuthorizationExample();
		example.createCriteria().andRoleIdEqualTo(roleId).andAuthorizationCodeEqualTo(authorizationCode);
		return roleAuthorizationMapper.deleteByExample(example);
	}

	@Override
	public int authorization(Integer roleId, String authorizationCode) {
		RoleAuthorization record = new RoleAuthorization();
		record.setRoleId(roleId);
		record.setAuthorizationCode(authorizationCode);
		return roleAuthorizationMapper.insert(record);
	}
}
