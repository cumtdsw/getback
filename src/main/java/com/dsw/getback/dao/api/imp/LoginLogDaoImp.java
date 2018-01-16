package com.dsw.getback.dao.api.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dsw.getback.dao.BaseDao;
import com.dsw.getback.dao.api.LoginLogDao;
import com.dsw.getback.domain.LoginLog;

@Repository
public class LoginLogDaoImp implements LoginLogDao {

	@Autowired
	protected BaseDao baseDao;

	@Override
	public void persitLoginLog(LoginLog loginLog) throws Exception {
		baseDao.persist("false", loginLog);
	}
	
}
