package com.dsw.getback.method.performance;

public class MethodPerformanceTest {
	public void test() {
		PerformanceMonitor.begin(MethodPerformanceTest.class, "test");
		try {
			System.out.println("abccc");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PerformanceMonitor.end(MethodPerformanceTest.class, "test");
	}
	public static void main(String[] args) {
		MethodPerformanceTest mpt = new MethodPerformanceTest();
		mpt.test();
	}

}
