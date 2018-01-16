package com.dsw.getback.dao;

import com.dsw.getback.domain.LoginLog;

/**
 * 记录登录日志DAO
 * @author Administrator
 *
 */
public interface LoginLogDao {
	
	public BaseDao getBaseDao();
	
	public void beginTransaction();
	
	public void commitTransaction();
	
	/**
	 * 记录登录日志
	 * @param loginLog
	 */
	public void insertLoginLog(LoginLog loginLog) throws Exception ;
	
}
