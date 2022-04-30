package com.gaop.appjob.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * redis 工具类
 */
@Component
public class JedisUtil {

	private static final Logger log = LoggerFactory.getLogger(JedisUtil.class);


	private JedisPool jedisPool;

	public void close(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	public boolean exists(String key) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.exists(key);
		} catch (Exception e) {
			log.error("exists " + key + " RedisException->" + e.getMessage(), e);
		} finally {
			this.close(jedis);
		}
		return result;
	}

	public String get(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis.exists(key)) {
				value = jedis.get(key);
				log.debug("get " + key + " = " + value);
			}
		} catch (Exception e) {
			log.error("get " + key + " = " + value + " RedisException->" + e.getMessage(), e);
		} finally {
			this.close(jedis);
		}
		return value;
	}

	public String set(String key, String value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.setex(key, cacheSeconds, value);
			log.debug("set " + key + " = " + value);
		} catch (Exception e) {
			log.error("set " + key + " = " + value + " RedisException->" + e.getMessage(), e);
		} finally {
			this.close(jedis);
		}
		return result;
	}

	public long del(String key) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis.exists(key)) {
				result = jedis.del(key);
				log.debug("del " + key);
			} else {
				log.debug("del " + key + " not exists");
			}
		} catch (Exception e) {
			log.error("key = " + key + " RedisException->" + e.getMessage(), e);
		} finally {
			this.close(jedis);
		}
		return result;
	}

	public boolean setnx(String key, String value, String nxxx, String expx, int time) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String ret = jedis.set(key, value, nxxx, expx, time);
			if ("OK".equalsIgnoreCase(ret)) {
				return true;
			}
		} catch (Exception e) {
			log.error("setnx " + key + " = " + value + " RedisException->" + e.getMessage(), e);
		} finally {
			this.close(jedis);
		}
		return result;
	}

	public boolean eval(String script, List<String> keys, List<String> args) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Object ret = jedis.eval(script, keys, args);
			Long res = 1L;
			if (res.equals(ret)) {
				return true;
			}
		} catch (Exception e) {
			log.error("eval " + script + " RedisException->" + e.getMessage(), e);
		} finally {
			this.close(jedis);
		}
		return result;
	}

}
