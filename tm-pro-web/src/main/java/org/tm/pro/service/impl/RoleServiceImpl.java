package org.tm.pro.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.tm.pro.entity.Role;
import org.tm.pro.mapper.RoleDao;
import org.tm.pro.service.RoleService;
import org.tm.pro.utils.TmMapUtil;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

	@Override
	@DataSource("slave")
	public Role getById(Integer id) {
		return baseMapper.selectById(id);
	}

	@Override
	@DataSource("slave")
	public Role getByCode(String code) {
		Role entity = new Role();
		entity.setRoleCode(code);
		return baseMapper.selectOne(entity);
	}

	@Override
	@DataSource("slave")
	public List<Role> getRoleList(Map<String, Object> params) {
		Wrapper<Role> wrapper = new EntityWrapper<Role>();
		Integer organizationId = TmMapUtil.getMapVal(params, "organizationId", 0);
		if (organizationId > 0) {
			wrapper.eq("organization_id", organizationId);
		}
		String roleName = TmMapUtil.getMapVal(params, "roleName", "");
		if (TmStringUtil.isNotBlank(roleName)) {
			wrapper.like("role_name", roleName);
		}
		String roleCode = TmMapUtil.getMapVal(params, "roleCode", "");
		if (TmStringUtil.isNotBlank(roleCode)) {
			wrapper.like("role_code", roleCode);
		}
		String usingState = TmMapUtil.getMapVal(params, "usingState", "");
		if (TmStringUtil.isNotBlank(usingState)) {
			wrapper.eq("using_state", usingState);
		}
		return baseMapper.selectList(wrapper);
	}

	@Override
	public int saveRole(Role role) {
		return baseMapper.insert(role);
	}

	@Override
	public int updateRole(Role role) {
		return baseMapper.updateById(role);
	}

	@Override
	public int deleteRole(Role role) {
		return baseMapper.deleteById(role.getId());
	}

}
