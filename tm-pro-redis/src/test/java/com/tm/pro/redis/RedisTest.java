package com.tm.pro.redis;

import org.junit.Test;

public class RedisTest {

	@Test
	public void testApp() {
		System.out.println(RedisUtil.getInstance().echo("Redis Ready"));
		// TmProObj obj = new TmProObj();
		// obj.setName("tx pro");
		// obj.setVersion("0.0.1-SNAPSHOT");
		// System.out.println(obj);

		// String res = RedisUtil.getInstance().setnx("version", "0.0.1-SNAPSHOT",
		// 1000L);
		// System.out.println(res);
		// RedisUtil.getInstance().ttl("version");
		// RedisUtil resis = RedisUtil.getInstance();
		// System.out.println(resis);
		// System.out.println(resis.setnx("version", "0.0.1-SNAPSHOT", 1000L));
		// System.out.println(resis.ttl("version"));
		// System.out.println(resis.setxx("version", "0.0.1-SNAPSHOT", 1000L));
		// System.out.println(resis.ttl("version"));

		// System.out.println(RedisUtil.getInstance().set("vs", "10"));
		// System.out.println(RedisUtil.getInstance().decr("vs"));
		// System.out.println(RedisUtil.getInstance().del("vs"));

		// Map<String, String> hash = new HashMap<>();
		// hash.put("name", "sizhongxia");
		// hash.put("age", "12");
		// hash.put("sex", "男");
		// System.out.println(RedisUtil.getInstance().hmset("hash", hash));

		// List<String> list = RedisUtil.getInstance().hmget("hash", "name", "age");
		// for (String s : list) {
		// System.out.println(s);
		// }

		// RedisUtil.getInstance().expire("hash", 1);
	}

}
