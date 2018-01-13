package com.dsw.getback.dao.imp;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dsw.getback.dao.BaseDao;
import com.dsw.getback.query.condition.BaseCondition;
import com.dsw.getback.query.helper.JPQL2Objects;
import com.dsw.getback.query.helper.ServiceHelper;
import com.dsw.getback.query.result.QueryResult;

/**
 * Session Bean implementation class EntityServiceBean
 */
@Repository
public class BaseDaoImp implements BaseDao {

	private static Logger logger = LogManager.getLogger(BaseDaoImp.class);

	/**
	 * EntityManager inject
	 */
	@Autowired
	protected EntityManager emRw;

	/** 持久化单元工具 */
	private PersistenceUnitUtil util;

	/** 获取持久化单元工具 */
	private PersistenceUnitUtil getUtil() {
		if (util == null) {
			util = emRw.getEntityManagerFactory().getPersistenceUnitUtil();
		}
		return util;
	}

	/**
	 * Default constructor.
	 */
	public BaseDaoImp() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * execute Native sql query
	 */
	public int executeNativeQuery(String nativeSql) {
		Query query = emRw.createNativeQuery(nativeSql);
		int result = query.executeUpdate();

		return result;
	}

	/**
	 * find object by id
	 */
	public <T> T find(String userToken, Class<T> cls, Object id) throws Exception {
		T result = null;
		result = emRw.find(cls, id);
		return result;
	}

	/**
	 * merge object
	 */
	public <T> T merge(String userToken, T o) throws Exception {
		Class<?> cls = o.getClass();
		Class<?> thisClazz = this.getClass();

		try {
			Method method = thisClazz.getDeclaredMethod("merge", cls);
			method.invoke(this, o);
			return null;
		} catch (NoSuchMethodException nsmex) {
			T result = emRw.merge(o);
			return result;
		}
	}

	// /////////////////////////////////////////////
	// 删除相关函数组， 开始
	// /////////////////////////////////////////////

	public <T> String remove(String userToken, Class<T> cls, Object id) throws Exception {
		if (cls == null || id == null) {
			logger.warn("parameter cls or id is null!");
			return null;
		}

		Object obj = emRw.find(cls, id);
		if (obj == null) {
			logger.warn("the identified[" + id + "] object of class[" + cls.getName() + "] is null!");
			return null;
		}
		return remove(userToken, obj);
	}

	/**
	 * Remove list
	 */

	public <T> String remove(String userToken, List<Object> objs) throws Exception {
		if (objs == null || objs.size() == 0) {
			logger.warn("objs is null");
			return null;
		}

		for (Object obj : objs) {
			remove(userToken, emRw.merge(obj));
		}

		return null;
	}

	/**
	 * remove object
	 */

	public String remove(String userToken, Object o) throws Exception {
		if (o == null) {
			logger.warn("the object o is null.");
			return null;
		}

		String result = "";
		Class<?> cls = o.getClass();
		Class<?> thisClazz = this.getClass();

		try {
			Method method = thisClazz.getDeclaredMethod("remove", cls);
			result = (String) method.invoke(this, o);
			return result;
		} catch (NoSuchMethodException nsmex) {
			emRw.remove(emRw.merge(o));
		}

		return result;
	}

	// /////////////////////////////////////////////
	// 删除相关函数组， 结束
	// /////////////////////////////////////////////

	// /////////////////////////////////////////////
	// 持久化相关函数组， 开始
	// /////////////////////////////////////////////

	public Object persist(String userToken, Object o) throws Exception {
		boolean has = true;
		if (has) {
			Class<?> cls = o.getClass();
			Class<?> thisClazz = this.getClass();

			try {
				Method method = thisClazz.getDeclaredMethod("persist", cls);
				return method.invoke(this, o);
			} catch (NoSuchMethodException nsmex1) {
				try {
					Method method = thisClazz.getDeclaredMethod("persist", String.class, cls);
					return method.invoke(this, userToken, o);
				} catch (NoSuchMethodException nsmex2) {
					emRw.persist(o);

					Object pk = getUtil().getIdentifier(o);
					o.getClass().getDeclaredMethod("setId", pk.getClass()).invoke(o, pk);
					return pk;
				}
			}
		} else
			return null;
	}

	// /////////////////////////////////////////////
	// 批量更新相关函数组，结束 //
	// /////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public <T> List<T> queryAll(String userToken, Class<T> cls) throws Exception {
		boolean had = true;
		if (!had) {
			return new ArrayList<T>();
		}
		String jpql = "select t from " + cls.getSimpleName() + " t where 1=1";
		Query query = emRw.createQuery(jpql);

		List<T> list = query.getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> query(String userToken, String jpql, Object... args) throws Exception {
		logger.info("now enter function EntityServiceBean.query().");
		logger.info("jpql is:" + jpql);

		JPQL2Objects paramMap = new JPQL2Objects(jpql, args);

		jpql = paramMap.getJpql();
		args = paramMap.getArgs();

		Query query = emRw.createQuery(jpql);
		for (int i = 0; i < args.length; ++i) {
			logger.info("arguments[" + i + "]=" + args[i]);
			query.setParameter(i + 1, args[i]);
		}

		List<T> list = query.getResultList();
		logger.info("query result size is:" + list.size());

		logger.info("now leave function EntityServiceBean.query().");
		return list;
	}

	@SuppressWarnings("unchecked")

	public <T> T uniqueQuery(String userToken, String jpql, Object... args) throws Exception {
		logger.info("now enter function EntityServiceBean.uniqueQuery().");
		logger.info("jpql is:" + jpql);
		logger.info("args is:" + args);

		JPQL2Objects paramMap = new JPQL2Objects(jpql, args);

		jpql = paramMap.getJpql();
		args = paramMap.getArgs();

		Query query = null;

		query = emRw.createQuery(jpql);
		for (int i = 0; i < args.length; ++i) {
			logger.info("arguments[" + i + "]=" + args[i]);
			query.setParameter(i + 1, args[i]);
		}
		query.setMaxResults(1);

		List<T> list = query.getResultList();
		T t = null;
		if (list.size() > 0)
			t = list.get(0);

		logger.info("now leave function EntityServiceBean.uniqueQuery().");

		return t;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> pageQuery(String userToken, String jpql, int start, int count, Object... args) throws Exception {
		logger.info("now enter function EntityServiceBean.PageQuery().");
		logger.info("jpql is:" + jpql);
		logger.info("start is:" + start);
		logger.info("count is:" + count);
		logger.info("args is:" + args);

		JPQL2Objects paramMap = new JPQL2Objects(jpql, args);

		jpql = paramMap.getJpql();
		args = paramMap.getArgs();

		Query query = emRw.createQuery(jpql);
		for (int i = 0; i < args.length; ++i) {
			logger.info("arguments[" + i + "]=" + args[i]);
			query.setParameter(i + 1, args[i]);
		}
		query.setFirstResult(start);
		query.setMaxResults(count);

		List<T> list = query.getResultList();
		logger.info("query result size is:" + list.size());

		logger.info("now leave function EntityServiceBean.PageQuery().");

		return list;
	}

	/**
	 * update
	 */

	public int updateQuery(String userToken, String jpql, Object... args) throws Exception {
		
		logger.info("now enter function EntityServiceBean.updateQuery().");
		logger.info("jpql is:" + jpql);
		logger.info("args is:" + args);

		JPQL2Objects paramMap = new JPQL2Objects(jpql, args);
		// paramMap = privilegeSvc.conditionFilter(userToken, paramMap,
		// Class.forName(entityName));
		jpql = paramMap.getJpql();
		args = paramMap.getArgs();

		Query query = emRw.createQuery(jpql);
		for (int i = 0; i < args.length; ++i) {
			logger.info("arguments[" + i + "]=" + args[i]);
			query.setParameter(i + 1, args[i]);
		}
		int rowCount = query.executeUpdate();

		logger.info("rowCount is:" + rowCount);
		logger.info("now leave function EntityServiceBean.updateQuery().");

		return rowCount;
	}

	@SuppressWarnings("unchecked")
	public QueryResult advQuery(String userToken, String sql, Object[] args, BaseCondition cond) throws Exception {
		logger.info("now enter function EntityServiceBean.advQuery().");
		logger.info("sql is:" + sql);
		logger.info("args is:" + Arrays.toString(args));
		if (cond != null) {
			logger.info("isPaged is:" + cond.isPaged());
			logger.info("currPage is:" + cond.getCurrPage());
			logger.info("pageSize is:" + cond.getPageSize());
			logger.info("maxPages is:" + cond.getMaxPages());
			logger.info("isQueryTotalPages is:" + cond.isQueryTotalPages());
		}

		Query query = emRw.createNamedQuery(sql);
		for (int i = 0; i < args.length; ++i) {
			logger.info("arguments[" + i + "]=" + args[i]);
			query.setParameter(i + 1, args[i]);
		}

		// check parameter
		if (cond == null) {
			logger.info("parameter cond is null.");
			throw new IllegalArgumentException("parameter cond is null.");
		}

		// check qmodel.
		if (cond.isPaged() && (cond.getCurrPage() <= 0 || cond.getPageSize() <= 0)) {
			logger.info("parameter currentPage or pageSize is invalid.");
			throw new IllegalArgumentException("parameter currentPage or pageSize is invalid.");
		}

		QueryResult result = new QueryResult();
		if (!cond.isPaged()) {
			result.setResults(query.getResultList());
			return result;
		}

		int currentPage = cond.getCurrPage();
		int pageSize = cond.getPageSize();
		int maxPages = (cond.getMaxPages() <= 0 ? 10 : cond.getMaxPages());
		int startPage = currentPage / maxPages * maxPages + (currentPage % maxPages == 0 ? -(maxPages - 1) : 1);

		int first = (startPage - 1) * pageSize;
		query.setFirstResult(first);
		query.setMaxResults(pageSize * (maxPages + 1));
		List<Object> listRtn = query.getResultList();

		// SplitFirstModel sf = new SplitFirstModel();
		int nCount = listRtn.size();
		int nPageNumber = nCount % pageSize > 0 ? (nCount / pageSize + 1) : (nCount / pageSize);

		// set paged info
		result.setTotalPages(nPageNumber + startPage - 1);
		result.setCurrPage(currentPage);
		result.setIsPaged(true);
		result.setPageSize(pageSize);
		result.setStartPage(startPage);
		int endPage = startPage + nPageNumber - 1;
		endPage = endPage > (startPage + maxPages - 1) ? (startPage + maxPages - 1) : endPage;
		result.setEndPage(endPage);
		result.setMaxPages(maxPages);

		int fromIdx = (currentPage - startPage) * pageSize;
		fromIdx = (fromIdx > nCount ? nCount : fromIdx);
		int toIdx = fromIdx + pageSize;
		toIdx = (toIdx > nCount ? nCount : toIdx);
		for (int i = fromIdx; i < toIdx; ++i) {
			result.addResult(listRtn.get(i));
		}

		logger.info("now leave function EntityServiceBean.advQuery().");

		return result;
	}

	@SuppressWarnings("unchecked")
	public QueryResult advQuery(String userToken, Class<?> returnType, String jpql, Object[] args, BaseCondition cond)
			throws Exception {
		logger.info("now enter function EntityServiceBean.advQuery().");
		logger.info("jpql is:" + jpql);
		logger.info("args is:" + Arrays.toString(args));
		if (cond != null) {
			logger.info("isPaged is:" + cond.isPaged());
			logger.info("currPage is:" + cond.getCurrPage());
			logger.info("pageSize is:" + cond.getPageSize());
			logger.info("maxPages is:" + cond.getMaxPages());
			logger.info("isQueryTotalPages is:" + cond.isQueryTotalPages());
		}

		JPQL2Objects paramMap = new JPQL2Objects(jpql, args);

		jpql = paramMap.getJpql();
		args = paramMap.getArgs();

		Query query = emRw.createQuery(jpql);
		for (int i = 0; i < args.length; ++i) {
			logger.info("arguments[" + i + "]=" + args[i]);
			query.setParameter(i + 1, args[i]);
		}

		// check parameter
		if (cond == null) {
			logger.info("parameter cond is null.");
			throw new IllegalArgumentException("parameter cond is null.");
		}

		// check qmodel.
		if (cond.isPaged() && (cond.getCurrPage() <= 0 || cond.getPageSize() <= 0)) {
			logger.info("parameter currentPage or pageSize is invalid.");
			throw new IllegalArgumentException("parameter currentPage or pageSize is invalid.");
		}

		QueryResult result = new QueryResult();
		int fromKwIdx = jpql.indexOf("from");
		String fromStr = jpql.substring(fromKwIdx);
		int orderByKwIdx = fromStr.indexOf(" order by ");
		fromStr = (orderByKwIdx == -1 ? fromStr : fromStr.substring(0, orderByKwIdx));
		String countJpql = "select count(*) " + fromStr;
		logger.info("countJpql is:" + countJpql);
		Query queryCount = emRw.createQuery(countJpql);
		for (int i = 0; i < args.length; ++i) {
			logger.info("arguments[" + i + "]=" + args[i]);
			queryCount.setParameter(i + 1, args[i]);
		}

		Long totalCount = (Long) queryCount.setMaxResults(1).getResultList().get(0);
		result.setTotalCount(totalCount);

		if (!cond.isPaged()) {
			result.setResults(query.getResultList());
			return result;
		}

		int pageSize = cond.getPageSize();
		if (cond.isQueryTotalPages()) {
			int totalPages = totalCount.intValue() % pageSize == 0 ? totalCount.intValue() / pageSize
					: totalCount.intValue() / pageSize + 1;
			result.setTotalPages(totalPages);
		}

		int currentPage = cond.getCurrPage();
		int maxPages = (cond.getMaxPages() <= 0 ? 10 : cond.getMaxPages());
		int startPage = currentPage / maxPages * maxPages + (currentPage % maxPages == 0 ? -(maxPages - 1) : 1);

		int first = (startPage - 1) * pageSize;
		query.setFirstResult(first);
		query.setMaxResults(pageSize * (maxPages + 1));
		List<Object> listRtn = query.getResultList();

		// SplitFirstModel sf = new SplitFirstModel();
		int nCount = listRtn.size();
		int nPageNumber = nCount % pageSize > 0 ? (nCount / pageSize + 1) : (nCount / pageSize);

		// set paged info
		// result.setTotalPages(nPageNumber + startPage - 1);
		result.setCurrPage(currentPage);
		result.setIsPaged(true);
		result.setPageSize(pageSize);
		result.setStartPage(startPage);
		int endPage = startPage + nPageNumber - 1;
		endPage = endPage > (startPage + maxPages - 1) ? (startPage + maxPages - 1) : endPage;
		result.setEndPage(endPage);
		result.setMaxPages(maxPages);

		int fromIdx = (currentPage - startPage) * pageSize;
		fromIdx = (fromIdx > nCount ? nCount : fromIdx);
		int toIdx = fromIdx + pageSize;
		toIdx = (toIdx > nCount ? nCount : toIdx);
		for (int i = fromIdx; i < toIdx; ++i) {
			result.addResult(ServiceHelper.ConvertEntity(listRtn.get(i), returnType));
		}

		logger.info("now leave function EntityServiceBean.advQuery().");

		return result;
	}

	@SuppressWarnings("unchecked")
	public QueryResult advQuery(String userToken, Class<?> returnType, Query query, BaseCondition cond) throws Exception {
		logger.info("now enter function EntityServiceBean.advQuery().");
		if (cond != null) {
			logger.info("isPaged is:" + cond.isPaged());
			logger.info("currPage is:" + cond.getCurrPage());
			logger.info("pageSize is:" + cond.getPageSize());
			logger.info("maxPages is:" + cond.getMaxPages());
			logger.info("isQueryTotalPages is:" + cond.isQueryTotalPages());
		}

		// check parameter
		if (cond == null) {
			logger.info("parameter cond is null.");
			throw new IllegalArgumentException("parameter cond is null.");
		}

		// check qmodel.
		if (cond.isPaged() && (cond.getCurrPage() <= 0 || cond.getPageSize() <= 0)) {
			logger.info("parameter currentPage or pageSize is invalide.");
			throw new IllegalArgumentException("parameter currentPage or pageSize is invalide.");
		}

		QueryResult result = new QueryResult();
		if (!cond.isPaged()) {
			result.setResults(query.getResultList());
			return result;
		}

		int currentPage = cond.getCurrPage();
		int pageSize = cond.getPageSize();
		int maxPages = (cond.getMaxPages() <= 0 ? 10 : cond.getMaxPages());
		int startPage = currentPage / maxPages * maxPages + (currentPage % maxPages == 0 ? -(maxPages - 1) : 1);

		int first = (startPage - 1) * pageSize;
		query.setFirstResult(first);
		query.setMaxResults(pageSize * (maxPages + 1));
		List<Object> listRtn = query.getResultList();

		// SplitFirstModel sf = new SplitFirstModel();
		int nCount = listRtn.size();
		int nPageNumber = nCount % pageSize > 0 ? (nCount / pageSize + 1) : (nCount / pageSize);

		// set paged info
		// result.setTotalPages(nPageNumber + startPage - 1);
		result.setCurrPage(currentPage);
		result.setIsPaged(true);
		result.setPageSize(pageSize);
		result.setStartPage(startPage);
		int endPage = startPage + nPageNumber - 1;
		endPage = endPage > (startPage + maxPages - 1) ? (startPage + maxPages - 1) : endPage;
		result.setEndPage(endPage);
		result.setMaxPages(maxPages);

		int fromIdx = (currentPage - startPage) * pageSize;
		fromIdx = (fromIdx > nCount ? nCount : fromIdx);
		int toIdx = fromIdx + pageSize;
		toIdx = (toIdx > nCount ? nCount : toIdx);
		for (int i = fromIdx; i < toIdx; ++i) {
			result.addResult(ServiceHelper.ConvertEntity(listRtn.get(i), returnType));
		}

		logger.info("now leave function EntityServiceBean.advQuery().");

		return result;
	}

	@Override
	public void beginTransaction() {
		emRw.getTransaction().begin();
	}

	@Override
	public void commitTransaction() {
		emRw.getTransaction().commit();
	}

	@Override
	public void close() {
		emRw.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public QueryResult advQuery(String userToken, Criteria query, BaseCondition cond) throws Exception {
		logger.info("now enter function BaseDaoImp.advQuery().");
		if (cond != null) {
			logger.info("isPaged is:" + cond.isPaged());
			logger.info("currPage is:" + cond.getCurrPage());
			logger.info("pageSize is:" + cond.getPageSize());
			logger.info("maxPages is:" + cond.getMaxPages());
			logger.info("isQueryTotalPages is:" + cond.isQueryTotalPages());
		}

		QueryResult result = new QueryResult();
		Class<?> returnType = null;
		if (query instanceof CriteriaImpl) {
			query.setCacheable(true);
			CriteriaImpl imp = (CriteriaImpl) query;
			String entityName = imp.getEntityOrClassName();
			returnType = Class.forName(entityName);
		}

		// check parameter
		if (cond == null) {
			logger.info("parameter cond is null.");
			throw new IllegalArgumentException("parameter cond is null.");
		}

		// check qmodel.
		if (cond.isPaged() && (cond.getCurrPage() <= 0 || cond.getPageSize() <= 0)) {
			logger.info("parameter currentPage or pageSize is invalide.");
			throw new IllegalArgumentException("parameter currentPage or pageSize is invalide.");
		}
		
		Long totalCount = (Long) query.setProjection(Projections.count("id")).uniqueResult();
		result.setTotalCount(totalCount);
		
		query.setProjection(null);
		if (cond.getIncludeFields() != null && cond.getIncludeFields().length > 0) {
			ProjectionList proList = Projections.projectionList();
			for (String field : cond.getIncludeFields()) {
				proList.add(Projections.property(field));
			}
			query.setProjection(proList);
			//query.setResultTransformer(Transformers.aliasToBean(returnType));
		}
		
		if (!cond.isPaged()) {
			result.setResults(query.list());
			return result;
		}

		int pageSize = cond.getPageSize();
		if (cond.isQueryTotalPages()) {
			int totalPages = totalCount.intValue() % pageSize == 0 ? totalCount.intValue() / pageSize
					: totalCount.intValue() / pageSize + 1;
			result.setTotalPages(totalPages);
		}

		int currentPage = cond.getCurrPage();
		int maxPages = (cond.getMaxPages() <= 0 ? 10 : cond.getMaxPages());
		int startPage = currentPage / maxPages * maxPages
				+ (currentPage % maxPages == 0 ? -(maxPages - 1) : 1);

		int first = (startPage - 1) * pageSize;
		query.setFirstResult(first);
		query.setMaxResults(pageSize * (maxPages + 1));
		List<Object> listRtn = query.list();
		
		// SplitFirstModel sf = new SplitFirstModel();
		int nCount = listRtn.size();

		int nPageNumber = nCount % pageSize > 0 ? (nCount / pageSize + 1) : (nCount / pageSize);

		// set paged info
		// result.setTotalPages(nPageNumber + startPage - 1);
		result.setCurrPage(currentPage);
		result.setIsPaged(true);
		result.setPageSize(pageSize);
		result.setStartPage(startPage);
		int endPage = startPage + nPageNumber - 1;
		endPage = endPage > (startPage + maxPages - 1) ? (startPage + maxPages - 1) : endPage;
		result.setEndPage(endPage);
		result.setMaxPages(maxPages);

		int fromIdx = (currentPage - startPage) * pageSize;
		fromIdx = (fromIdx > nCount ? nCount : fromIdx);
		int toIdx = fromIdx + pageSize;
		toIdx = (toIdx > nCount ? nCount : toIdx);
		for (int i = fromIdx; i < toIdx; ++i) {
			result.addResult(ServiceHelper.ConvertEntity(listRtn.get(i), cond.getIncludeFields(), returnType));
		}

		logger.info("now leave function BaseDaoImp.advQuery().");
		return result;
	}

}
