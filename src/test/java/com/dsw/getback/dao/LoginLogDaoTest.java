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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-jpa.xml","/spring-bean.xml" })
public class LoginLogDaoTest {
	private static Logger logger = Logger.getLogger(LoginLogDaoTest.class);
	
	@Autowired
	protected LoginLogDao loginLogDao;
	
	@Test
	public void insertLoginLogTest(){
		LoginLog loginLog = new LoginLog();
		loginLog.setId(UUID.randomUUID().toString());
		loginLog.setIp("10.1.1.1");
		loginLog.setLoginDate(new Date());
		loginLog.setUserId(UUID.randomUUID().toString());
		loginLogDao.beginTransaction();
		loginLogDao.insertLoginLog(loginLog);
		loginLogDao.commitTransaction();
		logger.info("okay");
	}

}
