package com.dsw.getback.service.api.monitor.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsw.getback.dao.BaseDao;
import com.dsw.getback.domain.MethodInvokeLog;
import com.dsw.getback.query.condition.BaseCondition;
import com.dsw.getback.query.result.QueryResult;
import com.dsw.getback.service.api.monitor.MethodInvokeLogService;

@Service
public class MethodInvokeLogServiceImp implements MethodInvokeLogService{
	
	@Autowired
	protected BaseDao baseDao;

	@Override
	public void addMethodInvokeLog(MethodInvokeLog mil) {
		try {
			baseDao.persist("false", mil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public QueryResult searchMethodInvokeLog(BaseCondition condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
