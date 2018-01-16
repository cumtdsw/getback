package com.dsw.getback.service.api;

import com.dsw.getback.domain.Users;

/**
 * 用户登录Service
 * @author Administrator
 *
 */

public interface UserService {
	
	/**
	 * 用户名和密码是否匹配
	 * @param userName 用户名
	 * @param password 密码
	 * @return ismatch
	 */
	public boolean isMatchUser(String userName, String password);
	
	/**
	 * 通过用户名查询用户信息
	 * @param userName
	 * @return 用户信息
	 */
	public Users searchUserByUserName(String userName) throws Exception;
	
	/**
	 * 记录登录状态
	 * @param user
	 */
	public void addLoginLog(Users user);

}
