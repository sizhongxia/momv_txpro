package com.tm.pro.redis.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tm.pro.utils.TmNumberUtil;
import org.tm.pro.utils.TmStringUtil;

import com.tm.pro.redis.config.RedisConfig;
import com.xiaoleilu.hutool.lang.Console;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * redis工具类
 * 
 * @author sizhongxia
 */
public class RedisSubPubUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisSubPubUtil.class);

	private static final String HOST_PORT_SEPARATOR = ":";
	private static final RedisSubPubUtil INSTANCE = new RedisSubPubUtil();

	private JedisPool jedisPool = null;

	private RedisSubPubUtil() {
		LOGGER.debug("init redis.");
		initRedis();
	}

	private void initRedis() {
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

		// 取得redis的url
		String redisUrl = RedisConfig.getConfigProperty("redis.jedisPoolConfig.urls");
		if (redisUrl == null || redisUrl.trim().isEmpty()) {
			throw new IllegalStateException("the urls of redis is not configured");
		}
		LOGGER.info("the urls of redis is {}", redisUrl);

		String[] redisUrlInfo = redisUrl.split(HOST_PORT_SEPARATOR);

		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMinIdle(minIdle);
		poolConfig.setMaxWaitMillis(maxWaitMillis);
		poolConfig.setTestOnBorrow(testOnBorrow);

		String auth = RedisConfig.getConfigProperty("redis.jedisPoolConfig.auth");
		if (TmStringUtil.isNotBlank(auth)) {
			this.jedisPool = new JedisPool(poolConfig, redisUrlInfo[0], TmNumberUtil.toInt(redisUrlInfo[1], 6379),
					timeout, auth);
		} else {
			this.jedisPool = new JedisPool(poolConfig, redisUrlInfo[0], TmNumberUtil.toInt(redisUrlInfo[1], 6379),
					timeout);
		}
	}

	public static RedisSubPubUtil getInstance() {
		return INSTANCE;
	}

	/**
	 * 订阅渠道
	 * 
	 * @param jedisPubSub
	 * @param channels
	 */
	public void subscribe(JedisPubSub jedisPubSub, String... channels) {
		new Thread(new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				Jedis jedis = jedisPool.getResource();
				try {
					// 使用subscriber订阅CHANNEL_NAME上的消息，这一句之后，线程进入订阅模式，阻塞。
					jedis.subscribe(jedisPubSub, channels);
					// 当unsubscribe()方法被调用时，才执行以下代码
					Console.log("Subscription ended.");
				} catch (Exception e) {
					if (jedis != null) {
						jedisPool.returnBrokenResource(jedis);
					}
					Console.error("Subscribing failed.", e);
				} finally {
					if (jedis != null) {
						jedisPool.returnResource(jedis);
					}
				}
			}
		}).start();
	}

	/**
	 * 向渠道发布信息
	 * 
	 * @param channel
	 * @param message
	 */
	@SuppressWarnings("deprecation")
	public void publish(String channel, String message) {
		Jedis jedis = jedisPool.getResource();
		jedis.publish(channel, message);
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}

}
