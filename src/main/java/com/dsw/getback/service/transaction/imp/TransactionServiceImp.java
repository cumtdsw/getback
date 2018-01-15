package com.dsw.getback.service.transaction.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsw.getback.dao.BaseDao;
import com.dsw.getback.service.transaction.TransactionService;

@Service
public class TransactionServiceImp implements TransactionService {

	@Autowired
	private BaseDao baseDao;

	@Override
	public void beginTransaction() {
		baseDao.beginTransaction();
	}

	@Override
	public void commitTransaction() {
		baseDao.commitTransaction();
	}

}
