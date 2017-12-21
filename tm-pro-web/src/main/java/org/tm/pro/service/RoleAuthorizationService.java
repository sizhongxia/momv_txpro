package org.tm.pro.service;

import java.util.Set;

import org.tm.pro.entity.RoleAuthorization;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 角色授权记录表 服务类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
public interface RoleAuthorizationService extends IService<RoleAuthorization> {
	Set<String> getRoleAuthorizations(Integer roleId);
}