package org.tm.pro.elastic.model;

import java.util.List;

import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;

public class RequestDateHistogram {
	private String field;
	private DateHistogramInterval interval;

	private List<RequestSubAggregation> subAggregations;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public DateHistogramInterval getInterval() {
		return interval;
	}

	public void setInterval(DateHistogramInterval interval) {
		this.interval = interval;
	}

	public List<RequestSubAggregation> getSubAggregations() {
		return subAggregations;
	}

	public void setSubAggregations(List<RequestSubAggregation> subAggregations) {
		this.subAggregations = subAggregations;
	}

}
