package org.tm.pro.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tm.pro.entity.Organization;
import org.tm.pro.entity.OrganizationExample;
import org.tm.pro.entity.OrganizationExample.Criteria;
import org.tm.pro.mapper.OrganizationMapper;
import org.tm.pro.service.OrganizationService;
import org.tm.pro.utils.TmMapUtil;
import org.tm.pro.utils.TmStringUtil;

import com.github.pagehelper.PageHelper;

@Service(value = "organizationService")
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	OrganizationMapper organizationMapper;

	@Override
	public Organization getById(Integer id) {
		return organizationMapper.selectByPrimaryKey(id);
	}

	@Override
	public long getOrganizationCount(Map<String, Object> params) {
		OrganizationExample example = initParams(params);
		return organizationMapper.countByExample(example);
	}

	private OrganizationExample initParams(Map<String, Object> params) {
		OrganizationExample example = new OrganizationExample();
		Criteria criteria = example.createCriteria();
		String organizationName = TmMapUtil.getMapVal(params, "organizationName", "");
		if (TmStringUtil.isNotBlank(organizationName)) {
			criteria.andOrganizationNameLike("%" + organizationName + "%");
		}
		return example;
	}

	@Override
	public List<Organization> getOrganizationList(Map<String, Object> params, int page, int size) {
		OrganizationExample example = initParams(params);
		example.setOrderByClause("sort_number asc");
		PageHelper.startPage(page, size);
		return organizationMapper.selectByExample(example);
	}

	@Override
	public int insert(Organization organization) {
		return organizationMapper.insert(organization);
	}

	@Override
	public int delete(Organization organization) {
		return organizationMapper.deleteByPrimaryKey(organization.getId());
	}

	@Override
	public int update(Organization organization) {
		return organizationMapper.updateByPrimaryKey(organization);
	}

	@Override
	public List<Organization> getOrganizationAll() {
		OrganizationExample example = new OrganizationExample();
		example.setOrderByClause("sort_number asc");
		return organizationMapper.selectByExample(example);
	}

}
