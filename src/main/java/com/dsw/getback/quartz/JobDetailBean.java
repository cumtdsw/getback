package com.dsw.getback.quartz;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class JobDetailBean extends QuartzJobBean {

	private static final Logger logger = LogManager.getLogger(JobDetailBean.class.getName());

	private String targetObject;
	private String targetMethod;
	private ApplicationContext ctx;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		try {
			Object bean = ctx.getBean(targetObject);
			Method m = bean.getClass().getMethod(targetMethod);
			m.invoke(bean, null);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.ctx = applicationContext;
	}

	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}
}
