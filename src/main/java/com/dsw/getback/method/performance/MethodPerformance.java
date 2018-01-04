package com.dsw.getback.method.performance;

import org.jboss.logging.Logger;

public class MethodPerformance {
	private static Logger logger = Logger.getLogger(MethodPerformance.class);
	private long beginTime;
	private long endTime;
	private String serviceMethod;
	public MethodPerformance(String serviceMethod) {
		this.serviceMethod = serviceMethod;
		beginTime = System.currentTimeMillis();
	}
	public void printPerformance() {
		endTime = System.currentTimeMillis();
		long elapse = endTime - beginTime;
		logger.info(serviceMethod + " invoked in " + elapse + "ms");
		
	}

}
