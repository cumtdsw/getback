package com.dsw.getback.service.api.monitor;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dsw.getback.domain.MethodInvokeLog;
import com.dsw.getback.service.transaction.TransactionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-jpa.xml", "/spring-bean.xml", "/spring-jms.xml", "/spring-redis.xml" })
public class MethodInvokeLogServiceTest {

	@Autowired
	protected MethodInvokeLogService methodInvokeLogService;

	@Autowired
	protected TransactionService transactionService;

	@Test
	public void save() {
		transactionService.beginTransaction();
		MethodInvokeLog mil = new MethodInvokeLog();
		mil.setId(UUID.randomUUID().toString());
		mil.setMethodInvokeEfficiency(100L);
		mil.setMethodInvokeTime(new Date());
		mil.setMethodInvokeName("save");

		methodInvokeLogService.addMethodInvokeLog(mil);
		transactionService.commitTransaction();
	}
}
