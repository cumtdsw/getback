package com.dsw.getback.service;

import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dsw.getback.domain.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-jpa.xml","/spring-bean.xml" })
public class UserServiceTest {
	private static Logger logger = Logger.getLogger(UserServiceTest.class);
	
	@Autowired
	protected UserService userService;

	@Test
	public void hasMatchUserTest() {
		boolean result = userService.hasMatchUser("admin", "admin");
		logger.info("count:" + result);
	}

	@Test
	public void findUserByUserNameTest() {
		Users user = userService.findUserByUserName("admin");
		logger.info("user:" + user.toString());
	}

	@Test
	public void loginSuccessTest() {
		Users user = userService.findUserByUserName("admin");
		userService.loginSuccess(user);
		logger.info("updateLoginInfoTest invoked..");
	}
	
	

}
