package com.dsw.getback.dao;

import java.util.Date;
import java.util.UUID;

import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dsw.getback.domain.LoginLog;
import com.dsw.getback.service.transaction.TransactionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-jpa.xml","/spring-bean.xml","/spring-jms.xml" })
public class LoginLogDaoTest {
	private static Logger logger = Logger.getLogger(LoginLogDaoTest.class);
	
	@Autowired
	protected LoginLogDao loginLogDao;
	
	@Autowired
	protected TransactionService transactionService;
	
	@Test
	public void insertLoginLogTest(){
		transactionService.beginTransaction();
		LoginLog loginLog = new LoginLog();
		loginLog.setId(UUID.randomUUID().toString());
		loginLog.setIp("10.1.1.1");
		loginLog.setLoginDate(new Date());
		loginLog.setUserId(UUID.randomUUID().toString());
		try {
			loginLogDao.insertLoginLog(loginLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("okay");
		transactionService.commitTransaction();
	}

}
