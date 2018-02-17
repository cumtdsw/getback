package com.dsw.getback.dao.api.imp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dsw.getback.dao.BaseDao;
import com.dsw.getback.dao.api.UserDao;
import com.dsw.getback.domain.Users;

@Repository
public class UserDaoImp implements UserDao {

	private static Logger logger = Logger.getLogger(UserDaoImp.class);

	@Autowired
	protected BaseDao baseDao;

	@Override
	public long queryMatchCount(String userName, String password) throws Exception {
		logger.info("userName:" + userName);
		logger.info("password:" + password);
		long count = 0;
		count = baseDao.uniqueQuery("false", "select count(*) from Users u where u.username=?1 and u.password=?2",
				userName, password);

		return count;
	}

	@Override
	public Users queryUserByUserName(String userName) throws Exception {
		Users users = null;
		users = baseDao.uniqueQuery("false", "from Users u where u.username=?1", userName);
		return users;
	}

	@Override
	public void updateLoginInfo(Users user) throws Exception {

		baseDao.updateQuery("false", "update Users u set u.lastVisit=?1, u.lastIp=?2 where u.id=?3",
				user.getLastVisit(), user.getLastIp(), user.getId());

	}
}
