package org.tm.pro.web.cache;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.tm.pro.entity.Authorization;
import org.tm.pro.entity.SystemInfo;
import org.tm.pro.service.AuthorizationService;
import org.tm.pro.service.SystemInfoService;
import org.tm.pro.web.event.TmApplicationEvent;

public class SystemInfoCacheUtil implements InitializingBean, ApplicationListener<TmApplicationEvent> {

	@Autowired
	SystemInfoService systemInfoService;
	@Autowired
	AuthorizationService authorizationService;

	// 父级权限字
	public static Vector<Authorization> parentAuthorizations = new Vector<>();
	// 子级权限字
	public static Vector<Authorization> childAuthorizations = new Vector<>();

	public static SystemInfo systemInfo = new SystemInfo();
	
	public static Authorization getAuthorizationByCode(String authorizationCode) {
		for(Authorization a : childAuthorizations) {
			if(a.getAuthorizationCode().equals(authorizationCode)) {
				return a;
			}
		}
		return null;
	}

	/**
	 * 初始化系统基本信息数据
	 */
	private void initSystemInfo() {
		systemInfo = systemInfoService.getDefaultInfo();
	}

	private void initAuthorizations() {
		parentAuthorizations.clear();
		childAuthorizations.clear();

		List<Authorization> ps = authorizationService.getAuthorization(true);
		if (ps != null && !ps.isEmpty()) {
			for (Authorization a : ps) {
				parentAuthorizations.add(a);
			}
		}
		List<Authorization> cs = authorizationService.getAuthorization(false);
		if (cs != null && !cs.isEmpty()) {
			for (Authorization a : cs) {
				childAuthorizations.add(a);
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initSystemInfo();
		// 初始化权限字数据
		initAuthorizations();
	}

	@Override
	public void onApplicationEvent(TmApplicationEvent e) {
		if ("UpdateSystemInfoCacheEvent".equals(e.getSource().toString())) {
			initSystemInfo();
		} else if ("UpdateAuthorizationCacheEvent".equals(e.getSource().toString())) {
			initAuthorizations();
		}
	}
}
