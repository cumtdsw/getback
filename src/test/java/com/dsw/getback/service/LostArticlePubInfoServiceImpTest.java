package com.dsw.getback.service;

import java.util.List;

import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dsw.getback.domain.LostArticlePubInfo;
import com.dsw.getback.query.condition.LostArticlePubCondition;
import com.dsw.getback.query.result.QueryResult;
import com.dsw.getback.service.api.LostArticlePubService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-jpa.xml","/spring-bean.xml","/spring-jms.xml" })
public class LostArticlePubInfoServiceImpTest {

	private static Logger logger = Logger.getLogger(LostArticlePubInfoServiceImpTest.class);
	@Autowired
	public LostArticlePubService lostArticlePubService;
	
	
	@Test
	public void queryLostArticleTest() {
		
		QueryResult result = lostArticlePubService.serachLostArticle(new LostArticlePubCondition());
		List<Object> list = result.getResults();
		for (Object obj : list) {
			LostArticlePubInfo lap = (LostArticlePubInfo) obj;
			logger.info("lap:" + lap);
		}
		logger.info("okay");
	}
}
