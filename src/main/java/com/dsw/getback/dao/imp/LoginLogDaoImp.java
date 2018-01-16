package com.dsw.getback.dao.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dsw.getback.dao.BaseDao;
import com.dsw.getback.dao.LoginLogDao;
import com.dsw.getback.domain.LoginLog;

@Repository
public class LoginLogDaoImp implements LoginLogDao {

	//private static Logger logger = LogManager.getLogger(LoginLogDaoImp.class);
	@Autowired
	protected BaseDao baseDao;

	@Override
	public void insertLoginLog(LoginLog loginLog) throws Exception {
		baseDao.persist("false", loginLog);
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
		return baseDao;
	}

}
