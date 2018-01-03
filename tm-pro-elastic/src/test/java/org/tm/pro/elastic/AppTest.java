//package org.tm.pro.elastic;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map.Entry;
//import java.util.Set;
//
//import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
//import org.tm.pro.elastic.model.AggregationEnum;
//import org.tm.pro.elastic.model.RequestDateHistogram;
//import org.tm.pro.elastic.model.RequestModel;
//import org.tm.pro.elastic.model.RequestSubAggregation;
//import org.tm.pro.elastic.model.ResponseAggrItem;
//import org.tm.pro.elastic.model.ResponseModel;
//import org.tm.pro.elastic.util.ElasticUtil;
//
///**
// * Unit test for simple App.
// */
//public class AppTest {
//	public static void main(String[] args) {
//		RequestModel requestModel = new RequestModel();
//
//		requestModel.setIndex("ppc_log_test-*");
//		requestModel.setType("access");
//
//		requestModel.setQmTimeField("@timestamp");
//		requestModel.setQmTimeStart("2017-09-30 23:00:00");
//		requestModel.setQmTimeEnd("2017-09-30 23:59:59");
//
//		requestModel.setFrom(0);
//		requestModel.setSize(0);
//
//		String[] terms = new String[] {};
//		requestModel.setAggrTerms(terms);
//
//		Set<RequestDateHistogram> dateHistograms = new HashSet<>();
//		RequestDateHistogram item = new RequestDateHistogram();
//		item.setField("@timestamp");
//		DateHistogramInterval interval = DateHistogramInterval.YEAR;
//		item.setInterval(interval);
//
//		List<RequestSubAggregation> subAggregations = new ArrayList<>();
//
//		RequestSubAggregation subAggregation = new RequestSubAggregation();
//		subAggregation.setField("dspeed");
//		subAggregation.setType(AggregationEnum.AVG);
//		subAggregations.add(subAggregation);
//
//		subAggregation = new RequestSubAggregation();
//		subAggregation.setField("dspeed");
//		subAggregation.setType(AggregationEnum.MAX);
//		subAggregations.add(subAggregation);
//
//		subAggregation = new RequestSubAggregation();
//		subAggregation.setField("dspeed");
//		subAggregation.setType(AggregationEnum.MIN);
//		subAggregations.add(subAggregation);
//
//		subAggregation = new RequestSubAggregation();
//		subAggregation.setField("dspeed");
//		subAggregation.setType(AggregationEnum.SUM);
//		subAggregations.add(subAggregation);
//
//		item.setSubAggregations(subAggregations);
//
//		dateHistograms.add(item);
//		requestModel.setDateHistograms(dateHistograms);
//
//		ResponseModel responseModel = ElasticUtil.baseAggregationProportion(requestModel);
//		if (responseModel.isSuccess()) {
//			for (String term : terms) {
//				List<ResponseAggrItem> aggrTerms = responseModel.getAggrTerms().get(term);
//				for (ResponseAggrItem aggrTerm : aggrTerms) {
//					System.out.println(aggrTerm.getKeyAsString() + " > " + aggrTerm.getDocCount());
//				}
//				System.out.println("====================================");
//			}
//			System.out.println();
//			System.out.println();
//			System.out.println();
//
//			for (RequestDateHistogram term : dateHistograms) {
//				List<ResponseAggrItem> aggrTerms = responseModel.getAggrDateHistograms().get(term.getField());
//				for (ResponseAggrItem aggrTerm : aggrTerms) {
//					System.out.print(aggrTerm.getKeyAsString() + " > " + aggrTerm.getDocCount());
//
//					for (Entry<String, Object> entry : aggrTerm.getValues().entrySet()) {
//						System.out.print(" > " + entry.getKey() + " : " + entry.getValue());
//					}
//
//					System.out.println();
//				}
//				System.out.println("====================================");
//			}
//			System.out.println();
//		}
//	}
//}
