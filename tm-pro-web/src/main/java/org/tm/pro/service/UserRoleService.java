package org.tm.pro.service;

import java.util.Set;

import org.tm.pro.entity.Role;
import org.tm.pro.entity.UserRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户角色记录表 服务类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
public interface UserRoleService extends IService<UserRole> {

	Set<String> getUserAuthorizations(Integer userId);

	Set<Role> getUserRoles(Integer userId);

	boolean checkUserRole(Integer userId, Integer roleId, Integer organizationId);
}
