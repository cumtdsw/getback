package com.dsw.getback.service;

import com.dsw.getback.domain.LostArticlePubInfo;
import com.dsw.getback.query.condition.LostArticlePubCondition;
import com.dsw.getback.query.result.QueryResult;

public interface LostArticlePubService {
	public QueryResult serachLostArticle(LostArticlePubCondition condition);
	
	public void publishLostArticle(LostArticlePubInfo lapi);

}
