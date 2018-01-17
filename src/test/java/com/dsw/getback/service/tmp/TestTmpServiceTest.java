package com.dsw.getback.service.tmp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-jpa.xml","/spring-bean.xml","/spring-jms.xml","/spring-quartz.xml" })
public class TestTmpServiceTest {
	
	@Autowired
	public TestTmpService testTmpService;
	
	@Test
	public void test() {
		System.out.println(testTmpService.isNull());
	}

}
