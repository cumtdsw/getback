package com.dsw.getback.dao.api;

import com.dsw.getback.domain.Users;

/**
 * 用户登录验证DAO
 * @author Administrator
 *
 */
public interface UserDao {
	
	/**
	 * 登录验证
	 * @param userName 用户名
	 * @param password 密码
	 * @return 匹配个数（验证用户是否存在)
	 */
	public long queryMatchCount(String userName, String password) throws Exception;
	
	public Users queryUserByUserName(final String userName) throws Exception;
	
	public void updateLoginInfo(Users user) throws Exception;

}
