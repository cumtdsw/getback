package com.dsw.getback.dao.imp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dsw.getback.dao.BaseDao;
import com.dsw.getback.dao.LoginLogDao;
import com.dsw.getback.domain.LoginLog;

@Repository
public class LoginLogDaoImp implements LoginLogDao {

	private static Logger logger = Logger.getLogger(LoginLogDaoImp.class);
	@Autowired
	protected BaseDao baseDao;
	
	@Override
	public void insertLoginLog(LoginLog loginLog) {
		try {
			baseDao.persist("false", loginLog);
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
