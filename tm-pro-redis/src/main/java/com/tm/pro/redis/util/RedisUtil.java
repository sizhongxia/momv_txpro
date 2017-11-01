package com.tm.pro.redis.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tm.pro.utils.TmNumberUtil;
import org.tm.pro.utils.TmStringUtil;

import com.tm.pro.redis.RedisExecutor;
import com.tm.pro.redis.config.RedisConfig;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;

/**
 * redis工具类
 * 
 * @author sizhongxia
 */
public class RedisUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

	private static final String HOST_PORT_SEPARATOR = ":";

	private ShardedJedisPool shardedJedisPool = null;

	private static final RedisUtil INSTANCE = new RedisUtil();

	private RedisUtil() {
		LOGGER.debug("init sharded redis pool.");
		initialShardedPool();
	}

	private void initialShardedPool() {
		// 操作超时时间,默认2秒
		int timeout = TmNumberUtil.toInt(RedisConfig.getConfigProperty("redis.timeout"), 2000);
		// jedis池最大连接数总数，默认8
		int maxTotal = TmNumberUtil.toInt(RedisConfig.getConfigProperty("redis.jedisPoolConfig.maxTotal"), 8);
		// jedis池最大空闲连接数，默认8
		int maxIdle = TmNumberUtil.toInt(RedisConfig.getConfigProperty("redis.jedisPoolConfig.maxIdle"), 8);
		// jedis池最少空闲连接数
		int minIdle = TmNumberUtil.toInt(RedisConfig.getConfigProperty("redis.jedisPoolConfig.minIdle"), 0);
		// jedis池没有对象返回时，最大等待时间单位为毫秒
		long maxWaitMillis = TmNumberUtil.toLong(RedisConfig.getConfigProperty("redis.jedisPoolConfig.maxWaitTime"),
				-1);
		// 在borrow一个jedis实例时，是否提前进行validate操作
		boolean testOnBorrow = Boolean
				.parseBoolean(RedisConfig.getConfigProperty("redis.jedisPoolConfig.testOnBorrow"));

		// 设置jedis连接池配置
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMinIdle(minIdle);
		poolConfig.setMaxWaitMillis(maxWaitMillis);
		poolConfig.setTestOnBorrow(testOnBorrow);

		// 取得redis的url
		String redisUrl = RedisConfig.getConfigProperty("redis.jedisPoolConfig.urls");
		if (redisUrl == null || redisUrl.trim().isEmpty()) {
			throw new IllegalStateException("the urls of redis is not configured");
		}
		LOGGER.info("the urls of redis is {}", redisUrl);

		String auth = RedisConfig.getConfigProperty("redis.jedisPoolConfig.auth");

		// 生成连接池
		List<JedisShardInfo> shardedPoolList = new ArrayList<JedisShardInfo>();
		String[] redisUrlInfo = redisUrl.split(HOST_PORT_SEPARATOR);
		JedisShardInfo Jedisinfo = new JedisShardInfo(redisUrlInfo[0], Integer.parseInt(redisUrlInfo[1]), timeout);
		if (TmStringUtil.isNotBlank(auth)) {
			Jedisinfo.setPassword(auth);
		}
		shardedPoolList.add(Jedisinfo);

		// 构造池
		this.shardedJedisPool = new ShardedJedisPool(poolConfig, shardedPoolList, Hashing.MURMUR_HASH);
	}

	public static RedisUtil getInstance() {
		return INSTANCE;
	}

	/**
	 * 实现jedis连接的获取和释放，具体的redis业务逻辑由executor实现
	 * 
	 * @param executor
	 *            RedisExecutor接口的实现类
	 * @return
	 */
	public <T> T execute(String key, RedisExecutor<T> executor) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		T result = null;
		try {
			result = executor.execute(jedis);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return result;
	}

	/***
	 * 设置值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(final String key, final String value) {
		return execute(key, new RedisExecutor<String>() {
			@Override
			public String execute(ShardedJedis jedis) {
				return jedis.set(key, value);
			}
		});
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @param nxxx
	 *            nxxx NX|XX, NX -- Only set the key if it does not already exist.
	 *            XX -- Only set the key if it already exist.
	 * 
	 * @param expx
	 *            expx EX|PX, expire time units: EX = seconds; PX = milliseconds
	 * @param time
	 * @return
	 */

	public String set(final String key, final String value, final String nxxx, final String expx, final long time) {
		return execute(key, new RedisExecutor<String>() {
			@Override
			public String execute(ShardedJedis jedis) {
				return jedis.set(key, value, nxxx, expx, time);
			}
		});
	}

	/***
	 * 当值不存在是设置值，并设置过期时间
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 */
	public String setnx(final String key, final String value, final long seconds) {
		return execute(key, new RedisExecutor<String>() {
			@Override
			public String execute(ShardedJedis jedis) {
				return jedis.set(key, value, "NX", "EX", seconds);
			}
		});
	}

	/***
	 * 只有不存在的时候才设置值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long setnx(final String key, final String value) {
		return execute(key, new RedisExecutor<Long>() {
			@Override
			public Long execute(ShardedJedis jedis) {
				return jedis.setnx(key, value);
			}
		});
	}

	/***
	 * 当值存在是设置值，并设置过期时间
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 */
	public String setxx(final String key, final String value, final long seconds) {
		return execute(key, new RedisExecutor<String>() {
			@Override
			public String execute(ShardedJedis jedis) {
				return jedis.set(key, value, "XX", "EX", seconds);
			}
		});
	}

	/***
	 * 存储字符串，并设置过期时间
	 * 
	 * @param key
	 * @param seconds
	 * @param value
	 * @return
	 */
	public String setex(final String key, final int seconds, final String value) {
		return execute(key, new RedisExecutor<String>() {
			@Override
			public String execute(ShardedJedis jedis) {
				return jedis.setex(key, seconds, value);
			}
		});
	}

	/***
	 * 获取字符串值
	 * 
	 * @param key
	 * @return
	 */
	public String get(final String key) {
		return execute(key, new RedisExecutor<String>() {
			@Override
			public String execute(ShardedJedis jedis) {
				return jedis.get(key);
			}
		});
	}

	/***
	 * 修改有效期时间（秒）
	 * 
	 * @param key
	 * @param seconds
	 * @return
	 */
	public Long expire(final String key, final int seconds) {
		return execute(key, new RedisExecutor<Long>() {
			@Override
			public Long execute(ShardedJedis jedis) {
				return jedis.expire(key, seconds);
			}
		});
	}

	/**
	 * 获取过期时间
	 * 
	 * @param key
	 * @return
	 */
	public Long ttl(final String key) {
		return execute(key, new RedisExecutor<Long>() {
			@Override
			public Long execute(ShardedJedis jedis) {
				return jedis.ttl(key);
			}
		});
	}

	/**
	 * 递增1
	 * 
	 * @param key
	 * @return
	 */
	public Long incr(final String key) {
		return execute(key, new RedisExecutor<Long>() {
			@Override
			public Long execute(ShardedJedis jedis) {
				return jedis.incr(key);
			}
		});
	}

	/**
	 * 递减1
	 * 
	 * @param key
	 * @return
	 */
	public Long decr(final String key) {
		return execute(key, new RedisExecutor<Long>() {
			@Override
			public Long execute(ShardedJedis jedis) {
				return jedis.decr(key);
			}
		});
	}

	/**
	 * 设置map值
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public Long hset(final String key, final String field, final String value) {
		return execute(key, new RedisExecutor<Long>() {
			@Override
			public Long execute(ShardedJedis jedis) {
				return jedis.hset(key, field, value);
			}
		});
	}

	/***
	 * 设置map
	 * 
	 * @param key
	 * @param hash
	 * @return
	 */
	public String hmset(final String key, final Map<String, String> hash) {
		return execute(key, new RedisExecutor<String>() {
			@Override
			public String execute(ShardedJedis jedis) {
				return jedis.hmset(key, hash);
			}
		});
	}

	/**
	 * 获取Map 某一值
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(final String key, final String field) {
		return execute(key, new RedisExecutor<String>() {
			@Override
			public String execute(ShardedJedis jedis) {
				return jedis.hget(key, field);
			}
		});
	}

	/**
	 * 返回哈希表中，一个或多个给定字段的值。
	 * 
	 * @param key
	 * @param fields
	 * @return
	 */
	public List<String> hmget(final String key, final String... fields) {
		return execute(key, new RedisExecutor<List<String>>() {
			@Override
			public List<String> execute(ShardedJedis jedis) {
				return jedis.hmget(key, fields);
			}
		});
	}

	/**
	 * 获取Map值
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> hgetAll(final String key) {
		return execute(key, new RedisExecutor<Map<String, String>>() {
			@Override
			public Map<String, String> execute(ShardedJedis jedis) {
				return jedis.hgetAll(key);
			}
		});
	}

	/***
	 * 检查Key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public Boolean exists(final String key) {
		return execute(key, new RedisExecutor<Boolean>() {
			@Override
			public Boolean execute(ShardedJedis jedis) {
				return jedis.exists(key);
			}
		});
	}

	/***
	 * 删除值
	 * 
	 * @param key
	 * @return
	 * 
	 * 		0 key不存在
	 */
	public Long del(final String key) {
		return execute(key, new RedisExecutor<Long>() {
			@Override
			public Long execute(ShardedJedis jedis) {
				return jedis.del(key);
			}
		});
	}

	/**
	 * 测试输出
	 * 
	 * @param key
	 * @return
	 */
	public String echo(final String str) {
		return execute(str, new RedisExecutor<String>() {
			@Override
			public String execute(ShardedJedis jedis) {
				return jedis.echo(str);
			}
		});
	}

	/**
	 * 销毁连接池
	 */
	public void destroy() {
		this.shardedJedisPool.close();
	}
}
