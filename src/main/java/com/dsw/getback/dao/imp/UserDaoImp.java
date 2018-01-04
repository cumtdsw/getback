package com.dsw.getback.dao.imp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dsw.getback.dao.BaseDao;
import com.dsw.getback.dao.UserDao;
import com.dsw.getback.domain.Users;

@Repository
public class UserDaoImp implements UserDao {
	
	private static Logger logger = Logger.getLogger(UserDaoImp.class);
	
	@Autowired
	protected BaseDao baseDao;

	@Override
	public long getMatchCount(String userName, String password) {
		logger.info("userName:" + userName);
		logger.info("password:" + password);
		long count = 0;
		try {
			count = baseDao.uniqueQuery("false", "select count(*) from Users u where u.username=?1 and u.password=?2", userName, password);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public Users findUserByUserName(String userName) {
		Users users = null;
		try {
			users = baseDao.uniqueQuery("false", "from Users u where u.username=?1", userName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return users;
	}

	@Override
	public void updateLoginInfo(Users user) {
		try {
			baseDao.updateQuery("false", "update Users u set u.lastVisit=?1, u.lastIp=?2 where u.id=?3", user.getLastVisit(), user.getLastIp(), user.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void beginTransaction() {
		baseDao.beginTransaction();
	}

	@Override
	public void commitTransaction() {
		baseDao.commitTransaction();
		
	}

	@Override
	public BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return baseDao;
	}

}
