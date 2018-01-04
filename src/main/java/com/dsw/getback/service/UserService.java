package com.dsw.getback.service;

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
	public boolean hasMatchUser(String userName, String password);
	
	/**
	 * 通过用户名查询用户信息
	 * @param userName
	 * @return 用户信息
	 */
	public Users findUserByUserName(String userName);
	
	/**
	 * 记录登录状态
	 * @param user
	 */
	public void loginSuccess(Users user);

}
