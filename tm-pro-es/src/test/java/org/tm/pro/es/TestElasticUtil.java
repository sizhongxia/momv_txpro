package org.tm.pro.es;

import org.tm.pro.es.entity.EsSearchResult;

public class TestElasticUtil {

	public static void main(String[] args) {
		ElasticUtil elasticUtil = new ElasticUtil();
		elasticUtil.setBaseUrl("http://127.0.0.1:9200/");
		System.out.println("start");
		EsSearchResult result = elasticUtil.avgDspeedStatistics("ppc_log_test*", "access", "2017-03-11", "2017-12-11", "1");
		System.out.println(result.getDatas().size());
	}

}
