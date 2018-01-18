package com.dsw.getback.service.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface CacheService {
	public void putCache(String key, Object value);

	/**
	 * 设置缓存，并设定缓存时长（秒）
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 */
	public void putCache(String key, Object value, int expire);

	/**
	 * 获取缓存数据
	 * 
	 * @param key
	 * @return
	 */
	public Object getCache(String key);

	/**
	 * 删除缓存
	 * 
	 * @param key
	 */
	public void removeCache(String key);
	/******************************
	 ********* 队列操作 ***********
	 ******************************/

	/**
	 * 队列缓存设置
	 * 
	 * @param key
	 * @param value
	 */
	public void putQueue(String key, Object value);

	/**
	 * 获取队列缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object getQueue(String key);

	/******************************
	 ********* 栈操作 ***********
	 ******************************/
	public void putStack(String key, Object value);

	public Object getStack(String key);

	public int length(String key);

	public void expire(String key, long timeout, TimeUnit unit);

	/******************************
	 ********* hash操作 ***********
	 ******************************/
	/**
	 * hash put all
	 * 
	 * @param key
	 * @param map
	 * @date 2015年10月12日
	 */
	public void hputs(String key, HashMap<? extends Object, ? extends Object> map);

	/**
	 * hash put
	 * 
	 * @param key
	 * @param hashKey
	 * @param value
	 * @date 2015年10月12日
	 */
	public void hput(String key, Object hashKey, Object value);

	/**
	 * hash get
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 * @date 2015年10月12日
	 */
	public Object hget(String key, Object hashKey);

	/**
	 * hash remove
	 * 
	 * @param key
	 * @param hashKey
	 * @date 2015年10月12日
	 */
	public void hrem(String key, Object hashKey);

	/**
	 * hash size
	 * 
	 * @param key
	 * @return
	 * @date 2015年10月12日
	 */
	public long hsize(String key);

	/**
	 * hash keys
	 * 
	 * @param key
	 * @return
	 * @date 2015年10月12日
	 */
	public Set<?> hkeys(String key);

	/**
	 * 取出Map
	 */
	public Map<Object, Object> hMap(String key);

	/************************************************************
	 ********************** zset 操作*****************************
	 ************************************************************/
	/**
	 * 往Zset插入数据
	 */
	public void zsetPut(String key, Object hashKey, Double score);

	/**
	 * 查询Zset,按照开始结束score
	 */
	public Set<?> zsetGet(String key, Double arg0, Double arg1);

	/**
	 * 模糊查询
	 */
	public Set<String> fuzzyQuery(String pattern);

}
