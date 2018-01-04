package com.dsw.getback.dao;

import com.dsw.getback.domain.LostArticlePubInfo;
import com.dsw.getback.query.condition.LostArticlePubCondition;
import com.dsw.getback.query.result.QueryResult;

public interface LostArticlePubDao {
	QueryResult queryLostArticle(LostArticlePubCondition condition);
	
	void addLostArticle(LostArticlePubInfo lap);
}
