package org.tm.pro.service;

import java.util.List;
import java.util.Map;

import org.tm.pro.entity.Organization;

public interface OrganizationService {

	int saveOrganization(Organization organization);

	Organization getById(Integer id);

	long getOrganizationCount(Map<String, Object> params);

	List<Organization> getOrganizationList(Map<String, Object> params, int page, int size);

	List<Organization> getOrganizationAll();

	int deleteOrganization(Organization organization);

	int updateOrganization(Organization organization);
}
