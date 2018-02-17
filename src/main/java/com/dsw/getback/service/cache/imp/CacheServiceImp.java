package com.dsw.getback.service.cache.imp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImp {
	protected Logger logger = Logger.getLogger(getClass());
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/*
	 * 缓存最大过期时间-一个月
	 */
	public static final int EXPIRE_TIME_MAX = 30 * 24 * 3600;

	/*
	 * 缓存过期时间-半天
	 */
	public static final int EXPIRE_TIME_HALFDAY = 12 * 3600;

	/*
	 * 缓存过期时间-整天
	 */
	public static final int EXPIRE_TIME_ONEDAY = 24 * 3600;

	/******************************
	 ********* 缓存操作 ***********
	 ******************************/

	/**
	 * 设置缓存
	 * 
	 * @param key
	 * @param value
	 */
	public void putCache(String key, Object value) {
		try {
			redisTemplate.opsForValue().set(key, value);
		} catch (Exception e) {
			logger.error("PUT cache exception [key=" + key + ", value=" + value + "].", e);
		}
	}

	/**
	 * 设置缓存，并设定缓存时长（秒）
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 */
	public void putCache(String key, Object value, int expire) {
		try {

			redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("PUT cache exception [key=" + key + ", value=" + value + ", expire=" + expire + "].", e);
		}
	}

	/**
	 * 获取缓存数据
	 * 
	 * @param key
	 * @return
	 */
	public Object getCache(String key) {
		try {

			return redisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			logger.error("GET cache exception [key=" + key + "].", e);
		}
		return null;
	}

	/**
	 * 删除缓存
	 * 
	 * @param key
	 */
	public void removeCache(String key) {
		try {

			redisTemplate.delete(key);

		} catch (Exception e) {
			logger.error("Remove cache exception [key=" + key + "].", e);
		}
	}

	/******************************
	 ********* 队列操作 ***********
	 ******************************/

	/**
	 * 队列缓存设置
	 * 
	 * @param key
	 * @param value
	 */
	public void putQueue(String key, Object value) {
		try {

			redisTemplate.opsForList().leftPush(key, value);

		} catch (Exception e) {
			logger.error("PUT Queue cache exception [key=" + key + ", value=" + value + "].", e);
		}
	}

	/**
	 * 获取队列缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object getQueue(String key) {
		try {

			return redisTemplate.opsForList().rightPop(key);

		} catch (Exception e) {
			logger.error("GET Queue cache exception [key=" + key + "].", e);
			return null;
		}
	}

	/******************************
	 ********* 栈操作 ***********
	 ******************************/
	public void putStack(String key, Object value) {
		try {
			redisTemplate.opsForList().leftPush(key, value);
		} catch (Exception e) {
			logger.error("PUT Stack cache exception [key=" + key + ", value=" + value + "].", e);
		}
	}

	public Object getStack(String key) {
		try {
			return redisTemplate.opsForList().leftPop(key);

		} catch (Exception e) {
			logger.error("GET Stack cache exception [key=" + key + "].", e);
			return null;
		}
	}

	public int length(String key) {

		try {
			return redisTemplate.opsForList().size(key).intValue();
		} catch (Exception e) {
			logger.error("GET cache length exception [key=" + key + "].", e);
			return 0;
		}
	}

	public void expire(String key, long timeout, TimeUnit unit) {
		try {
			redisTemplate.expire(key, timeout, unit);
		} catch (Exception e) {
			logger.error("SET expire time exception [key=" + key + "].", e);
		}
	}

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
	public void hputs(String key, HashMap<? extends Object, ? extends Object> map) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
		} catch (Exception e) {
			logger.error("PUT All Hash exception [key=" + key + "].", e);
		}
	}

	/**
	 * hash put
	 * 
	 * @param key
	 * @param hashKey
	 * @param value
	 * @date 2015年10月12日
	 */
	public void hput(String key, Object hashKey, Object value) {
		try {
			redisTemplate.opsForHash().put(key, hashKey, value);
		} catch (Exception e) {
			logger.error("PUT Hash length exception [key=" + key + "].", e);
		}
	}

	/**
	 * hash get
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 * @date 2015年10月12日
	 */
	public Object hget(String key, Object hashKey) {
		try {
			return redisTemplate.opsForHash().get(key, hashKey);
		} catch (Exception e) {
			logger.error("GET Hash exception [key=" + key + "].", e);
			return null;
		}
	}

	/**
	 * hash remove
	 * 
	 * @param key
	 * @param hashKey
	 * @date 2015年10月12日
	 */
	public void hrem(String key, Object hashKey) {
		try {
			redisTemplate.opsForHash().delete(key, hashKey);
		} catch (Exception e) {
			logger.error("DELETE Hash exception [key=" + key + "].", e);
		}
	}

	/**
	 * hash size
	 * 
	 * @param key
	 * @return
	 * @date 2015年10月12日
	 */
	public long hsize(String key) {
		try {
			return redisTemplate.opsForHash().size(key);
		} catch (Exception e) {
			logger.error("GET Hash size exception [key=" + key + "].", e);
			return 0;
		}
	}

	/**
	 * hash keys
	 * 
	 * @param key
	 * @return
	 * @date 2015年10月12日
	 */
	public Set<?> hkeys(String key) {
		try {
			return redisTemplate.opsForHash().keys(key);
		} catch (Exception e) {
			logger.error("GET Hash size exception [key=" + key + "].", e);
			return null;
		}
	}

	/**
	 * 取出Map
	 */
	public Map<Object, Object> hMap(String key) {
		try {
			return redisTemplate.opsForHash().entries(key);
		} catch (Exception e) {
			logger.error("GET Map size exception [key=" + key + "].", e);
			return null;
		}
	}

	/************************************************************
	 ********************** zset 操作*****************************
	 ************************************************************/
	/**
	 * 往Zset插入数据
	 */
	public void zsetPut(String key, Object hashKey, Double score) {
		try {
			redisTemplate.opsForZSet().add(key, hashKey, score);
		} catch (Exception e) {
			logger.error("PUT Zset exception [key=" + key + "].", e);
		}
	}

	/**
	 * 查询Zset,按照开始结束score
	 */
	public Set<?> zsetGet(String key, Double arg0, Double arg1) {
		try {
			return redisTemplate.opsForZSet().rangeByScore(key, arg0, arg1);
		} catch (Exception e) {
			logger.error("GET Zset exception [key=" + key + "].", e);
			return null;
		}
	}

	/**
	 * 模糊查询
	 */
	public Set<String> fuzzyQuery(String pattern) {
		try {
			return redisTemplate.keys(pattern);
		} catch (Exception e) {
			logger.error("GET fuzzyQuery exception [key=" + pattern + "].", e);
			return null;
		}
	}

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
