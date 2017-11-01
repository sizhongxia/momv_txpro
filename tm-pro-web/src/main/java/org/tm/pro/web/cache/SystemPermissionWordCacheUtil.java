package org.tm.pro.web.cache;

import java.util.Vector;

import org.springframework.beans.factory.InitializingBean;
import org.tm.pro.model.SystemPermissionWordModel;

public class SystemPermissionWordCacheUtil implements InitializingBean {

	public static Vector<SystemPermissionWordModel> permissionWords = new Vector<SystemPermissionWordModel>();

	/***
	 * 初始化系统权限字信息
	 */
	private void initSystemPermissionWord() {
		if (!permissionWords.isEmpty()) {
			permissionWords.clear();
		}
		permissionWords.add(new SystemPermissionWordModel("系统菜单", "概览", "system_menu_manage_index"));
		permissionWords.add(new SystemPermissionWordModel("系统菜单", "用户", "system_menu_manage_users"));
		permissionWords.add(new SystemPermissionWordModel("系统菜单", "消息", "system_menu_manage_notices"));
		permissionWords.add(new SystemPermissionWordModel("系统菜单", "系统", "system_menu_manage_system"));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initSystemPermissionWord();
	}

}
