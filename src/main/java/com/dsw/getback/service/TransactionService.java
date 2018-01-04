package com.dsw.getback.service;

public interface TransactionService {
	/**
	 * 开启事务
	 */
	void beginTransaction();

	/**
	 * 提交事务
	 */
	void commitTransaction();

}
