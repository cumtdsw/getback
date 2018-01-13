package com.dsw.getback.dao.imp;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dsw.getback.dao.BaseDao;
import com.dsw.getback.dao.LostArticlePubDao;
import com.dsw.getback.domain.LostArticlePubInfo;
import com.dsw.getback.query.condition.LostArticlePubCondition;
import com.dsw.getback.query.helper.ServiceHelper;
import com.dsw.getback.query.result.QueryResult;

@Repository
public class LostArticlePubDaoImp implements LostArticlePubDao{

	private static Logger logger = LogManager.getLogger(LostArticlePubDaoImp.class);
	
	@Autowired
	protected  BaseDao baseDao;
	
	@Autowired
	protected EntityManager emRw;
	
	@Override
	public QueryResult queryLostArticle(LostArticlePubCondition condition) {
		//logger.info("now enter function LostArticlePubDaoImp.queryLostArticle");
		if (condition == null) {
			logger.error("condition is null");
			return new QueryResult();
		}
		logger.info("keywords is: " + condition.getKeywords());
		Session se = emRw.unwrap(Session.class);
		Criteria criteria = se.createCriteria(LostArticlePubInfo.class);
		if (null != condition.getKeywords() && !"".equals(condition.getKeywords())) {
			criteria.add(Restrictions.like("name", "%" + condition.getKeywords().trim() + "%").ignoreCase());
		}
		ServiceHelper.addOrder(criteria, condition.getOrders());
		QueryResult reuslt = null;
		try {
			reuslt = baseDao.advQuery("false", criteria, condition);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		//logger.info("now leave function LostArticlePubDaoImp.queryLostArticle");
		return reuslt;
	}

	@Override
	public void addLostArticle(LostArticlePubInfo lap) {
		try {
			baseDao.persist("false", lap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
