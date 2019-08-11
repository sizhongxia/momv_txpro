package org.tm.pro.es;

public class ConstantDSL {

	public static String queryAggsStatusCode(String startDate, String endDate, String cacheDeviceIp) {
		if ((startDate == null || endDate == null) && cacheDeviceIp == null) {
			return queryAggsStatusCode();
		}
		if (cacheDeviceIp == null) {
			return queryAggsStatusCode(startDate, endDate);
		}
		if (startDate == null || endDate == null) {
			return queryAggsStatusCode(cacheDeviceIp);
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[{\"range\":{\"@timestamp\":{\"gte\":\"");
		sb.append(startDate);
		sb.append(" 00:00:00\",\"lte\":\"");
		sb.append(endDate);
		sb.append(
				" 23:59:59\",\"format\":\"yyyy-MM-dd HH:mm:ss\",\"include_lower\":false,\"include_upper\":true}}},{\"match_phrase\":{\"cache_device_ip\":{\"query\":\"");
		sb.append(cacheDeviceIp);
		sb.append(
				"\"}}}]}},\"aggregations\":{\"gb\":{\"terms\":{\"field\":\"status_code\"}}}}");
		return sb.toString();
	}

	private static String queryAggsStatusCode(String startDate, String endDate) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[{\"range\":{\"@timestamp\":{\"gte\":\"");
		sb.append(startDate);
		sb.append(" 00:00:00\",\"lte\":\"");
		sb.append(endDate);
		sb.append(
				" 23:59:59\",\"format\":\"yyyy-MM-dd HH:mm:ss\",\"include_lower\":false,\"include_upper\":true}}}]}},\"aggregations\":{\"gb\":{\"terms\":{\"field\":\"status_code\"}}}}");
		return sb.toString();
	}

	private static String queryAggsStatusCode(String cacheDeviceIp) {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[{\"match_phrase\":{\"cache_device_ip\":{\"query\":\"");
		sb.append(cacheDeviceIp);
		sb.append(
				"\"}}}]}},\"aggregations\":{\"gb\":{\"terms\":{\"field\":\"status_code\"}}}}");
		return sb.toString();
	}

	private static String queryAggsStatusCode() {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[]}},\"aggregations\":{\"gb\":{\"terms\":{\"field\":\"status_code\"}}}}");
		return sb.toString();
	}

	public static String queryAggsCacheStat(String startDate, String endDate, String cacheDeviceIp) {
		if ((startDate == null || endDate == null) && cacheDeviceIp == null) {
			return queryAggsCacheStat();
		}
		if (cacheDeviceIp == null) {
			return queryAggsCacheStat(startDate, endDate);
		}
		if (startDate == null || endDate == null) {
			return queryAggsCacheStat(cacheDeviceIp);
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[{\"range\":{\"@timestamp\":{\"gte\":\"");
		sb.append(startDate);
		sb.append(" 00:00:00\",\"lte\":\"");
		sb.append(endDate);
		sb.append(
				" 23:59:59\",\"format\":\"yyyy-MM-dd HH:mm:ss\",\"include_lower\":false,\"include_upper\":true}}},{\"match_phrase\":{\"cache_device_ip\":{\"query\":\"");
		sb.append(cacheDeviceIp);
		sb.append(
				"\"}}}]}},\"aggregations\":{\"gb\":{\"terms\":{\"field\":\"cache_stat\"}}}}");
		return sb.toString();
	}

	private static String queryAggsCacheStat(String startDate, String endDate) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[{\"range\":{\"@timestamp\":{\"gte\":\"");
		sb.append(startDate);
		sb.append(" 00:00:00\",\"lte\":\"");
		sb.append(endDate);
		sb.append(
				" 23:59:59\",\"format\":\"yyyy-MM-dd HH:mm:ss\",\"include_lower\":false,\"include_upper\":true}}}]}},\"aggregations\":{\"gb\":{\"terms\":{\"field\":\"cache_stat\"}}}}");
		return sb.toString();
	}

	private static String queryAggsCacheStat(String cacheDeviceIp) {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[{\"match_phrase\":{\"cache_device_ip\":{\"query\":\"");
		sb.append(cacheDeviceIp);
		sb.append(
				"\"}}}]}},\"aggregations\":{\"gb\":{\"terms\":{\"field\":\"cache_stat\"}}}}");
		return sb.toString();
	}

	private static String queryAggsCacheStat() {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[]}},\"aggregations\":{\"gb\":{\"terms\":{\"field\":\"cache_stat\"}}}}");
		return sb.toString();
	}
	
	public static String queryAvgDspeed(String startDate, String endDate, String cacheDeviceIp) {
		if ((startDate == null || endDate == null) && cacheDeviceIp == null) {
			return queryAvgDspeed();
		}
		if (cacheDeviceIp == null) {
			return queryAvgDspeed(startDate, endDate);
		}
		if (startDate == null || endDate == null) {
			return queryAvgDspeed(cacheDeviceIp);
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[{\"range\":{\"@timestamp\":{\"gte\":\"");
		sb.append(startDate);
		sb.append(" 00:00:00\",\"lte\":\"");
		sb.append(endDate);
		sb.append(
				" 23:59:59\",\"format\":\"yyyy-MM-dd HH:mm:ss\",\"include_lower\":false,\"include_upper\":true}}},{\"match_phrase\":{\"cache_device_ip\":{\"query\":\"");
		sb.append(cacheDeviceIp);
		sb.append(
				"\"}}}]}},\"aggregations\":{\"av\":{\"date_histogram\":{\"field\":\"@timestamp\",\"interval\":\"300s\",\"format\":\"yyyy-MM-dd HH:mm\"},\"aggs\":{\"av\":{\"avg\":{\"field\":\"dspeed\"}}}}}}");
		return sb.toString();
	}

	private static String queryAvgDspeed(String startDate, String endDate) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[{\"range\":{\"@timestamp\":{\"gte\":\"");
		sb.append(startDate);
		sb.append(" 00:00:00\",\"lte\":\"");
		sb.append(endDate);
		sb.append(
				" 23:59:59\",\"format\":\"yyyy-MM-dd HH:mm:ss\",\"include_lower\":false,\"include_upper\":true}}}]}},\"aggregations\":{\"av\":{\"date_histogram\":{\"field\":\"@timestamp\",\"interval\":\"300s\",\"format\":\"yyyy-MM-dd HH:mm\"},\"aggs\":{\"av\":{\"avg\":{\"field\":\"dspeed\"}}}}}}");
		return sb.toString();
	}

	private static String queryAvgDspeed(String cacheDeviceIp) {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[{\"match_phrase\":{\"cache_device_ip\":{\"query\":\"");
		sb.append(cacheDeviceIp);
		sb.append(
				"\"}}}]}},\"aggregations\":{\"av\":{\"date_histogram\":{\"field\":\"@timestamp\",\"interval\":\"300s\",\"format\":\"yyyy-MM-dd HH:mm\"},\"aggs\":{\"av\":{\"avg\":{\"field\":\"dspeed\"}}}}}}");
		return sb.toString();
	}

	private static String queryAvgDspeed() {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[]}},\"aggregations\":{\"av\":{\"date_histogram\":{\"field\":\"@timestamp\",\"interval\":\"300s\",\"format\":\"yyyy-MM-dd HH:mm\"},\"aggs\":{\"av\":{\"avg\":{\"field\":\"dspeed\"}}}}}}");
		return sb.toString();
	}
	
	public static String queryCachePreSecond(String startDate, String endDate, String cacheDeviceIp) {
		if ((startDate == null || endDate == null) && cacheDeviceIp == null) {
			return queryCachePreSecond();
		}
		if (cacheDeviceIp == null) {
			return queryCachePreSecond(startDate, endDate);
		}
		if (startDate == null || endDate == null) {
			return queryCachePreSecond(cacheDeviceIp);
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[{\"range\":{\"@timestamp\":{\"gte\":\"");
		sb.append(startDate);
		sb.append(" 00:00:00\",\"lte\":\"");
		sb.append(endDate);
		sb.append(
				" 23:59:59\",\"format\":\"yyyy-MM-dd HH:mm:ss\",\"include_lower\":false,\"include_upper\":true}}},{\"match_phrase\":{\"cache_device_ip\":{\"query\":\"");
		sb.append(cacheDeviceIp);
		sb.append(
				"\"}}}]}},\"aggregations\":{\"gb\":{\"date_histogram\":{\"field\":\"@timestamp\",\"interval\":\"5m\",\"format\":\"yyyy-MM-dd HH:mm\"}}}}");
		return sb.toString();
	}

	private static String queryCachePreSecond(String startDate, String endDate) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[{\"range\":{\"@timestamp\":{\"gte\":\"");
		sb.append(startDate);
		sb.append(" 00:00:00\",\"lte\":\"");
		sb.append(endDate);
		sb.append(
				" 23:59:59\",\"format\":\"yyyy-MM-dd HH:mm:ss\",\"include_lower\":false,\"include_upper\":true}}}]}},\"aggregations\":{\"gb\":{\"date_histogram\":{\"field\":\"@timestamp\",\"interval\":\"5m\",\"format\":\"yyyy-MM-dd HH:mm\"}}}}");
		return sb.toString();
	}

	private static String queryCachePreSecond(String cacheDeviceIp) {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[{\"match_phrase\":{\"cache_device_ip\":{\"query\":\"");
		sb.append(cacheDeviceIp);
		sb.append(
				"\"}}}]}},\"aggregations\":{\"gb\":{\"date_histogram\":{\"field\":\"@timestamp\",\"interval\":\"5m\",\"format\":\"yyyy-MM-dd HH:mm\"}}}}");
		return sb.toString();
	}

	private static String queryCachePreSecond() {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[]}},\"aggregations\":{\"gb\":{\"date_histogram\":{\"field\":\"@timestamp\",\"interval\":\"5m\",\"format\":\"yyyy-MM-dd HH:mm\"}}}}");
		return sb.toString();
	}
	
	

}
