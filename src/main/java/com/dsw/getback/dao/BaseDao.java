package com.dsw.getback.dao;

import java.util.List;

import javax.persistence.Query;

import com.dsw.getback.query.condition.BaseCondition;
import com.dsw.getback.query.result.QueryResult;

/**
 * 基础数据操作DAO
 * 
 * @author Administrator
 *
 */
public interface BaseDao {

	/**
	 * 开启事务
	 */
	void beginTransaction();

	/**
	 * 提交事务
	 */
	void commitTransaction();

	/**
	 * 关闭EntityManager
	 */
	void close();

	/**
	 * 执行 Native Sql
	 * 
	 * @param nativeSql
	 *            - native sql 语句
	 * @return - 执行结果
	 */
	int executeNativeQuery(String nativeSql);

	/**
	 * 查找指定类型实体对象
	 * 
	 * @param userToken
	 *            - 用户令牌
	 * @param useWriteSession
	 *            - 使用持久化单元chilopod-write关联的Sesison
	 * @param cls
	 *            - 实体类型
	 * @param id
	 *            - 实体标识
	 * @return 实体对象
	 */
	public <T> T find(String userToken, Class<T> cls, Object id) throws Exception;

	/**
	 * 更新指定实体对象
	 * 
	 * @param userToken
	 *            - 用户令牌
	 * @param o
	 *            - 实体对象
	 * @return 更新后的实体对象
	 * @throws Exception
	 */
	<T> T merge(String userToken, T o) throws Exception;

	/**
	 * 删除指定实体对象
	 * 
	 * @param o
	 *            实体对象
	 * @return 返回错误消息，如果返回为null,则删除成功，否则失败。
	 */
	String remove(String userToken, Object o) throws Exception;

	/**
	 * 删除指定实体标识对应的实体对象
	 * 
	 * @param userToken
	 *            - 用户令牌
	 * @param cls
	 *            - 实体类型
	 * @param id
	 *            - 实体标识
	 * @return 返回错误消息，如果返回为null,则删除成功，否则失败。
	 */
	<T> String remove(String userToken, Class<T> cls, Object id) throws Exception;

	/**
	 * 删除指定实体数组
	 */
	<T> String remove(String userToken, List<Object> objs) throws Exception;

	/**
	 * 保存指定实体对象
	 * 
	 * @param userToken
	 *            用户令牌
	 * @param o
	 *            实体对象
	 * @return 实体主键值
	 * @throws Exception
	 */
	Object persist(String userToken, Object o) throws Exception;

	/**
	 * 根据实体类型查询该类型的所有对象
	 * 
	 * @param cls
	 *            实体类型
	 * @return 指定类型实体集合
	 */
	<T> List<T> queryAll(String userToken, Class<T> cls) throws Exception;

	/**
	 * 按条件查询实体对象
	 * 
	 * @param userToken
	 *            用户令牌
	 * @param jpql
	 *            JPQL语句
	 * @param args
	 *            变参集合
	 * @return 查询结果集合列表
	 * @throws Exception
	 */
	<T> List<T> query(String userToken, String jpql, Object... args) throws Exception;

	/**
	 * 按条件查询唯一实体对象
	 * 
	 * @param userToken
	 *            用户令牌
	 * @param jpql
	 *            JPQL语句
	 * @param args
	 *            变参集合
	 * @return 查询的唯一实体对象，如果没有返回null
	 */
	<T> T uniqueQuery(String userToken, String jpql, Object... args) throws Exception;

	/**
	 * 执行更新查询
	 * 
	 * @param jpql
	 *            JPQL语句
	 * @param args
	 *            变参集合
	 * @return 更新查询影响条数
	 * @throws Exception
	 */
	int updateQuery(String userToken, String jpql, Object... args) throws Exception;

	/**
	 * 按条件分页查询实体对象
	 * 
	 * @param jpql
	 * @param start
	 * @param count
	 * @param args
	 * @return
	 * @throws Exception
	 */
	<T> List<T> pageQuery(String userToken, String jpql, int start, int count, Object... args) throws Exception;

	/**
	 * 按查询条件进行查询
	 * 
	 * @param userToken
	 *            用户令牌
	 * @param returnType
	 *            返回类型
	 * @param jpql
	 *            JPQL语句
	 * @param args
	 *            变参集合
	 * @param cond
	 *            查询条件
	 * @return 查询结果
	 * @throws Exception
	 */
	QueryResult advQuery(String userToken, Class<?> returnType, String jpql, Object[] args, BaseCondition cond)
			throws Exception;

	/**
	 * 按查询条件进行查询
	 * 
	 * @param userToken
	 *            用户令牌
	 * @param sql
	 *            SQL语句
	 * @param args
	 *            变参集合
	 * @param cond
	 *            查询条件
	 * @return 查询结果
	 * @throws Exception
	 */
	QueryResult advQuery(String userToken, String sql, Object[] args, BaseCondition cond) throws Exception;

	/**
	 * 按查询条件进行查询
	 * 
	 * @param userToken
	 *            用户令牌
	 * @param returnType
	 *            返回类型
	 * @param query
	 *            查询条件
	 * @param cond
	 *            查询条件
	 * @return 查询结果
	 * @throws Exception
	 */
	QueryResult advQuery(String userToken, Class<?> returnType, Query query, BaseCondition cond) throws Exception;

	/**
	 * 高级查询
	 * 
	 * @param query
	 * @param cond
	 * @return
	 * @throws Exception
	 */
	public QueryResult advQuery(String userToken, org.hibernate.Criteria query, BaseCondition cond) throws Exception;
}
