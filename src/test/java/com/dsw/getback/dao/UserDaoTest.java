package com.dsw.getback.dao;

import java.util.Date;

import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dsw.getback.dao.api.LoginLogDao;
import com.dsw.getback.dao.api.UserDao;
import com.dsw.getback.domain.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-jpa.xml", "/spring-bean.xml", "/spring-jms.xml" })
public class UserDaoTest {
	private static Logger logger = Logger.getLogger(UserDaoTest.class);

	@Autowired
	protected UserDao userDao;

	@Autowired
	protected LoginLogDao loginLogDao;

	@Test
	public void getMatchCountTest() {
		long count = 0;
		try {
			count = userDao.queryMatchCount("admin", "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("count:" + count);
	}

	@Test
	public void findUserByUserNameTest() {
		Users user = null;
		try {
			user = userDao.queryUserByUserName("admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("user:" + user.toString());
	}

	@Test
	public void updateLoginInfoTest() {
		Users user;
		try {
			user = userDao.queryUserByUserName("admin");

			user.setLastIp("10.1.1.1");
			user.setLastVisit(new Date());
			userDao.updateLoginInfo(user);
			logger.info("updateLoginInfoTest invoked..");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void baseDaoIsEqualsTest() {
	}

	@Test
	public void test() {
		String str = "a,b,c,,d";
		String[] ar = str.split(",");
		System.out.println(ar.length);
	}

}
