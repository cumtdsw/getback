package com.dsw.getback.service.api.monitor;

import com.dsw.getback.domain.MethodInvokeLog;
import com.dsw.getback.query.condition.BaseCondition;
import com.dsw.getback.query.result.QueryResult;

public interface MethodInvokeLogService {
	
	public void addMethodInvokeLog(MethodInvokeLog mil);
	
	public QueryResult searchMethodInvokeLog(BaseCondition condition);

}
