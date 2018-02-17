package com.dsw.getback.service.api.imp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsw.getback.dao.api.LostArticlePubDao;
import com.dsw.getback.domain.LostArticlePubInfo;
import com.dsw.getback.query.condition.LostArticlePubCondition;
import com.dsw.getback.query.result.QueryResult;
import com.dsw.getback.service.api.LostArticlePubService;

@Service
public class LostArticlePubServiceImp implements LostArticlePubService{
	private static Logger logger = Logger.getLogger(LostArticlePubServiceImp.class);
	
	@Autowired
	protected LostArticlePubDao lostArticlePubDao;

	@Override
	public QueryResult serachLostArticle(LostArticlePubCondition condition) {
		if (condition == null) {
			logger.error("condition is not allowed to be null");
			return new QueryResult();
		}
		QueryResult result = lostArticlePubDao.queryLostArticle(condition);
	
		return result;
	}

	@Override
	public void addLostArticle(LostArticlePubInfo lapi) {
		lostArticlePubDao.persitLostArticle(lapi);
	}

}
