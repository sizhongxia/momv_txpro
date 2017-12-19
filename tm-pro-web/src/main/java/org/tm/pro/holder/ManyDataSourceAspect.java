package org.tm.pro.holder;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tm.pro.web.anno.DataSource;

public class ManyDataSourceAspect {
	private static Logger logger = LoggerFactory.getLogger(ManyDataSourceAspect.class);

	public void switchDB(JoinPoint point) throws Exception {
		// 拦截的实体类
		Object target = point.getTarget();
		// 拦截的方法
		String methodName = point.getSignature().getName();
		// 拦截的参数
		Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
		Class<?>[] clazzs = target.getClass().getInterfaces();
		Class<?> clazz = null;
		if (clazzs != null && clazzs.length > 0) {
			clazz = clazzs[0];
		}

		DataSource ds = null;
		// 拿到类的方法级别注解
		Method m = target.getClass().getMethod(methodName, parameterTypes);
		if (m != null && m.isAnnotationPresent(DataSource.class)) {
			ds = m.getAnnotation(DataSource.class);
			if (ds != null && StringUtils.isNotBlank(ds.value())) {
				// logger.warn(target.getClass() + " Use " + ds.value() + "DataSource.");
				CustomerContextHolder.setCustomerType(ds.value());
				return;
			}
		}

		// 拿接口中的方法级别的注解
		m = clazz.getMethod(methodName, parameterTypes);
		if (m != null && m.isAnnotationPresent(DataSource.class)) {
			ds = m.getAnnotation(DataSource.class);
			if (ds != null && StringUtils.isNotBlank(ds.value())) {
				// logger.warn(target.getClass() + " Use " + ds.value() + "DataSource.");
				CustomerContextHolder.setCustomerType(ds.value());
				return;
			}
		}

		// 拿到类级别的注解
		ds = (DataSource) clazz.getAnnotation(DataSource.class);
		if (ds != null && StringUtils.isNotBlank(ds.value())) {
			// logger.warn(target.getClass() + " Use " + ds.value() + "DataSource.");
			CustomerContextHolder.setCustomerType(ds.value());
			return;
		}
		logger.warn(target.getClass() + " Not Config DataSource.");

	}
}
