package com.dsw.getback.service.api.imp;

import java.util.Date;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsw.getback.dao.LoginLogDao;
import com.dsw.getback.dao.UserDao;
import com.dsw.getback.domain.LoginLog;
import com.dsw.getback.domain.Users;
import com.dsw.getback.service.api.UserService;

@Service
public class UserServiceImp implements UserService {
	private static Logger logger = LogManager.getLogger(UserServiceImp.class);

	@Autowired
	protected UserDao userDao;

	@Autowired
	protected LoginLogDao loginLogDao;

	@Override
	public boolean hasMatchUser(String userName, String password) {
		long count = 0;
		try {
			count = userDao.getMatchCount(userName, password);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return count > 0;
	}

	@Override
	public Users findUserByUserName(String userName) {
		Users user = null;
		try {
			user = userDao.findUserByUserName(userName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		System.out.println("system out test");
		logger.info("user:" + user);
		return user;
	}

	@Override
	public void loginSuccess(Users user) {
		logger.info("userDao.getBaseDao() == loginLogDao.getBaseDao() is:" + (userDao.getBaseDao() == loginLogDao.getBaseDao()));
		try {
			userDao.updateLoginInfo(user);
			LoginLog loginLog = new LoginLog();
			loginLog.setId(UUID.randomUUID().toString());
			loginLog.setIp("10.1.1.1");
			loginLog.setLoginDate(new Date());
			loginLog.setUserId(user.getId());
			loginLogDao.insertLoginLog(loginLog);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
