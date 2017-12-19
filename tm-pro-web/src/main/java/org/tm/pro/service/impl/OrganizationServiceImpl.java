package org.tm.pro.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.tm.pro.entity.Organization;
import org.tm.pro.mapper.OrganizationDao;
import org.tm.pro.service.OrganizationService;
import org.tm.pro.utils.TmMapUtil;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 组织机构 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class OrganizationServiceImpl extends ServiceImpl<OrganizationDao, Organization> implements OrganizationService {

	@Override
	public int saveOrganization(Organization entity) {
		return baseMapper.insert(entity);
	}

	@Override
	@DataSource("slave")
	public Organization getById(Integer id) {
		return baseMapper.selectById(id);
	}

	@Override
	@DataSource("slave")
	public long getOrganizationCount(Map<String, Object> params) {
		Wrapper<Organization> wrapper = new EntityWrapper<Organization>();

		String organizationName = TmMapUtil.getMapVal(params, "organizationName", "");
		if (TmStringUtil.isNotBlank(organizationName)) {
			wrapper.like("organization_name", "%" + organizationName + "%");
		}

		return baseMapper.selectCount(wrapper);
	}

	@Override
	@DataSource("slave")
	public List<Organization> getOrganizationList(Map<String, Object> params, int page, int size) {
		Wrapper<Organization> wrapper = new EntityWrapper<Organization>();

		String organizationName = TmMapUtil.getMapVal(params, "organizationName", "");
		if (TmStringUtil.isNotBlank(organizationName)) {
			wrapper.like("organization_name", "%" + organizationName + "%");
		}

		RowBounds rowBounds = new RowBounds((page - 1) * size, size);
		return baseMapper.selectPage(rowBounds, wrapper);
	}

	@Override
	@DataSource("slave")
	public List<Organization> getOrganizationAll() {
		Wrapper<Organization> wrapper = new EntityWrapper<>();
		return baseMapper.selectList(wrapper);
	}

	@Override
	public int deleteOrganization(Organization organization) {
		return baseMapper.deleteById(organization.getId());
	}

	@Override
	public int updateOrganization(Organization organization) {
		return baseMapper.updateById(organization);
	}

}
