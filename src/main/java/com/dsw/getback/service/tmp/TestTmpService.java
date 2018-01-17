package com.dsw.getback.service.tmp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestTmpService {
	@Autowired
	protected org.apache.commons.dbcp.BasicDataSource dataSource;

	public boolean isNull() {
		return dataSource == null;
	}

}
