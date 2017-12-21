package org.tm.pro.service.impl;

import org.springframework.stereotype.Service;
import org.tm.pro.entity.Organization;
import org.tm.pro.mapper.OrganizationDao;
import org.tm.pro.service.OrganizationService;
import org.tm.pro.web.anno.DataSource;

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
}