package com.dsw.getback.method.performance;

import org.apache.log4j.Logger;

public class MethodPerformance {
	private static Logger logger = Logger.getLogger(MethodPerformance.class);
	private long beginTime;
	private long endTime;
	private String serviceMethod;
	public MethodPerformance(String serviceMethod) {
		this.serviceMethod = serviceMethod;
		beginTime = System.currentTimeMillis();
	}
	public long printPerformance() {
		endTime = System.currentTimeMillis();
		long elapse = endTime - beginTime;
		logger.info(serviceMethod + " invoked in " + elapse + "ms");
		return elapse;
		
	}

}
