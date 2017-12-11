//package com.tm.pro.redis.util;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.tm.pro.utils.TmNumberUtil;
//
//import com.tm.pro.redis.config.RedisConfig;
//import com.xiaoleilu.hutool.io.file.FileWriter;
//
//public class RedisUtilTest {
//
//	public static void main(String[] args) {
//		RedisUtil redisUtil = new RedisUtil();
//
//		RedisConfig redisConfig = new RedisConfig();
//		redisConfig.setUrls("120.25.192.56:6379");
//		redisConfig.setAuth("xiafengfeiwu123");
//		redisConfig.setMaxIdle(8);
//		redisConfig.setMaxTotal(8);
//		redisConfig.setTestOnBorrow(true);
//		redisConfig.setTimeout(30000);
//		redisConfig.setMaxWaitMillis(60000);
//		redisConfig.setMinIdle(3);
//		redisUtil.setRedisConfig(redisConfig);
//
//		try {
//			redisUtil.afterPropertiesSet();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		Map<String, String> res = redisUtil.hgetAll("tx:domain:available");
//
//		List<String> domains = new ArrayList<>();
//		for (String s : res.keySet()) {
//			domains.add(s);
//		}
//
//		res = null;
//
//		Collections.sort(domains, new Comparator<String>() {
//			@Override
//			public int compare(String o1, String o2) {
//				String f1 = o1.split("[.]")[0];
//				String f2 = o2.split("[.]")[0];
//				return Integer.parseInt(f1, 36) - Integer.parseInt(f2, 36);
//			}
//		});
//
////		Set<String> s = new HashSet<>();
////		for (String d : domains) {
////			String ds = d.split("[.]")[0];
////			if (s.add(ds)) {
////				if (TmNumberUtil.isNumber(ds)) {
////					System.out.println(ds);
////				}
////			}
////		}
//
//		 FileWriter writer = new FileWriter("D:\\domains.txt");
//		 writer.appendLines(domains);
//		 domains = null;
//	}
//
//}
