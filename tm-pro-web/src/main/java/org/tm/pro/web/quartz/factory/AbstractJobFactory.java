package org.tm.pro.web.quartz.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.quartz.model.Job;
import org.tm.pro.web.utils.SpringBeanUtil;

public class AbstractJobFactory {

	public void invoke(Job job) {
		if (!TmStringUtil.isBlank(job.getJobClassName())) {
			try {
				Class<?> clazz = Class.forName(job.getJobClassName());
				Object obj = SpringBeanUtil.getBeanByType(clazz);
				Method method = obj.getClass().getDeclaredMethod("run");
				method.invoke(obj);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}