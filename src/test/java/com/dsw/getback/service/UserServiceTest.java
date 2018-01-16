package com.dsw.getback.service;

import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dsw.getback.domain.Users;
import com.dsw.getback.service.api.UserService;
import com.dsw.getback.service.transaction.TransactionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-jpa.xml","/spring-bean.xml","/spring-jms.xml" })
public class UserServiceTest {
	private static Logger logger = Logger.getLogger(UserServiceTest.class);
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected TransactionService transactionService;

	@Test
	public void hasMatchUserTest() {
		boolean result = userService.isMatchUser("admin", "admin");
		logger.info("count:" + result);
	}

	@Test
	public void findUserByUserNameTest() throws Exception {
		Users user = userService.searchUserByUserName("admin");
		logger.info("user:" + user.toString());
	}

	@Test
	public void loginSuccessTest() throws Exception {
		transactionService.beginTransaction();
		Users user = userService.searchUserByUserName("admin");
		userService.addLoginLog(user);
		logger.info("updateLoginInfoTest invoked..");
		transactionService.commitTransaction();
	}
	
	
	

}
