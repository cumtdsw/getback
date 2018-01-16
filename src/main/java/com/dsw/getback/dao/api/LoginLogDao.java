package com.dsw.getback.dao.api;

import com.dsw.getback.domain.LoginLog;

/**
 * 记录登录日志DAO
 * @author Administrator
 *
 */
public interface LoginLogDao {
	
	/**
	 * 记录登录日志
	 * @param loginLog
	 */
	public void persitLoginLog(LoginLog loginLog) throws Exception ;
	
}
