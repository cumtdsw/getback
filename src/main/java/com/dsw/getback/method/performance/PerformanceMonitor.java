package com.dsw.getback.method.performance;

import org.jboss.logging.Logger;

public class PerformanceMonitor {
	private static Logger logger = Logger.getLogger(PerformanceMonitor.class);
	
	//通过一个ThreadLocal保存调用线程相关的性能监视信息
	private static ThreadLocal<MethodPerformance> performanceRecord = new ThreadLocal<MethodPerformance>();
	
	//启动对某一目标方法的性能监视
	@SuppressWarnings("rawtypes")
	public static void begin(Class claszz, String method) {
		String clazzMethod = claszz.getName().toString() +"." + method;
		logger.info("now enter method "+ clazzMethod);
		MethodPerformance mp = new MethodPerformance(clazzMethod);
		performanceRecord.set(mp);
	}
	
	@SuppressWarnings("rawtypes")
	public static void end(Class claszz, String method) {
		String clazzMethod = claszz.getName().toString() +"." + method;
		logger.info("now leave method "+ clazzMethod);
		MethodPerformance mp = performanceRecord.get();
		mp.printPerformance();
	}

}
