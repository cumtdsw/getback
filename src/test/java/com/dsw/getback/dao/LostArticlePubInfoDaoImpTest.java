package com.dsw.getback.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dsw.getback.constants.Constants;
import com.dsw.getback.dao.api.LostArticlePubDao;
import com.dsw.getback.domain.LostArticlePubInfo;
import com.dsw.getback.query.condition.LostArticlePubCondition;
import com.dsw.getback.query.result.QueryResult;
import com.dsw.getback.util.FileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-jpa.xml", "/spring-bean.xml", "/spring-jms.xml", "/spring-redis.xml" })
public class LostArticlePubInfoDaoImpTest {

	private static Logger logger = Logger.getLogger(LoginLogDaoTest.class);
	@Autowired
	public LostArticlePubDao lostArticlePubDao;

	@Autowired
	public BaseDao baseDao;

	@Test
	public void queryLostArticleTest() {

		QueryResult result = lostArticlePubDao.queryLostArticle(new LostArticlePubCondition());
		List<Object> list = result.getResults();
		for (Object obj : list) {
			LostArticlePubInfo lap = (LostArticlePubInfo) obj;
			logger.info("lap:" + lap);
		}
		logger.info("okay");
	}

	@Test
	public void resourceTest() {
		Resource res = new ClassPathResource("1.jpg");
		try {
			logger.info(res.contentLength());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void addLostArticleInfoTest() {
		LostArticlePubInfo lapi = new LostArticlePubInfo();
		lapi.setId(UUID.randomUUID().toString());
		lapi.setLaname("test-name");
		byte[] bytes = FileUtils.file2Bytes("1.jpg", Constants.TYPE_PATH_CLASSPATH);
		lapi.setLapics(bytes);
		lapi.setLapubTime(new Date());
		baseDao.beginTransaction();
		lostArticlePubDao.persitLostArticle(lapi);
		baseDao.commitTransaction();
	}

}
