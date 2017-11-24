package org.tm.pro.web.utils;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.tm.pro.utils.TmStringUtil;

public final class SpringBeanUtil {

	// 获取web容器上下文
	private static WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();

	public static Object getBeanByName(String beanId) throws Exception {
		if (TmStringUtil.isBlank(beanId)) {
			throw new Exception("beanId is null");
		}
		return wac.getBean(beanId);

	}
	@SuppressWarnings("unchecked")
	public static <T> T getBeanByType(Class<?> clazz) {
		if (clazz == null) {
			return null;
		}
		return (T) wac.getBean(clazz);
	}
}
