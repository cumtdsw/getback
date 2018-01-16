package com.dsw.getback.aspectj;

import java.util.Date;
import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dsw.getback.domain.MethodInvokeLog;
import com.dsw.getback.method.performance.PerformanceMonitor;
import com.dsw.getback.service.api.monitor.MethodInvokeLogService;

@Component
@Aspect
public class ServiceMethodAspect {
	
	@Autowired
	protected MethodInvokeLogService methodInvokeLogService;
	
	@Around(value="execution(* com.dsw.getback.service.api.imp.*.*(..))")
	public Object aroundMethodInvoke(ProceedingJoinPoint pjp) throws Throwable {
		String methodName = pjp.getSignature().getName();
		PerformanceMonitor.begin(pjp.getTarget().getClass(), methodName);
		Object obj = pjp.proceed();
		long mills = PerformanceMonitor.end(pjp.getTarget().getClass(), methodName);
		MethodInvokeLog mil = new MethodInvokeLog();
		mil.setId(UUID.randomUUID().toString());
		mil.setMethodInvokeEfficiency(mills);
		mil.setMethodInvokeTime(new Date());
		mil.setMethodInvokeName(methodName);
		methodInvokeLogService.addMethodInvokeLog(mil);
		return obj;
	}

}
