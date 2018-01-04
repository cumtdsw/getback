package com.dsw.getback.service.imp;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsw.getback.dao.LoginLogDao;
import com.dsw.getback.dao.UserDao;
import com.dsw.getback.domain.LoginLog;
import com.dsw.getback.domain.Users;
import com.dsw.getback.service.UserService;

@Service
public class UserServiceImp implements UserService {
	private static Logger logger = Logger.getLogger(UserServiceImp.class);
	
	@Autowired
	protected UserDao userDao;
	
	@Autowired 
	protected LoginLogDao loginLogDao;

	@Override
	public boolean hasMatchUser(String userName, String password) {
		long count = userDao.getMatchCount(userName, password);
		return count > 0;
	}

	@Override
	public Users findUserByUserName(String userName) {
		Users user = userDao.findUserByUserName(userName);
		logger.info("user:" + user);
		return user;
	}

	@Override
	public void loginSuccess(Users user) {
		logger.info("userDao.getBaseDao() == loginLogDao.getBaseDao() is:" + (userDao.getBaseDao() == loginLogDao.getBaseDao()));
		userDao.beginTransaction();
		userDao.updateLoginInfo(user);
		LoginLog loginLog = new LoginLog();
		loginLog.setId(UUID.randomUUID().toString());
		loginLog.setIp("10.1.1.1");
		loginLog.setLoginDate(new Date());
		loginLog.setUserId(user.getId());
		loginLogDao.insertLoginLog(loginLog);
		userDao.commitTransaction();

	}

}
