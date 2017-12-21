package org.tm.pro.service.impl;

import org.springframework.stereotype.Service;
import org.tm.pro.entity.Role;
import org.tm.pro.mapper.RoleDao;
import org.tm.pro.service.RoleService;
import org.tm.pro.web.anno.DataSource;

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
}