package com.dsw.getback.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.dsw.getback.method.performance.PerformanceMonitor;

@Component
@Aspect
public class JobMethodAspect {
	
	@Around(value="execution(* com.dsw.getback.quartz.*.*(..))")
	public Object aroundMethodInvoke(ProceedingJoinPoint pjp) throws Throwable {
		String methodName = pjp.getSignature().getName();
		PerformanceMonitor.begin(pjp.getTarget().getClass(), methodName);
		Object obj = pjp.proceed();
		PerformanceMonitor.end(pjp.getTarget().getClass(), methodName);
		return obj;
	}

}
