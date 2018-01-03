package org.tm.pro.elastic.model;

import java.util.Map;

public class ResponseAggrItem {
	private Object key;
	private String keyAsString;
	private long docCount;
	// 子聚合值
	private Map<String, Double> values;

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public String getKeyAsString() {
		return keyAsString;
	}

	public void setKeyAsString(String keyAsString) {
		this.keyAsString = keyAsString;
	}

	public long getDocCount() {
		return docCount;
	}

	public void setDocCount(long docCount) {
		this.docCount = docCount;
	}

	public Map<String, Double> getValues() {
		return values;
	}

	public void setValues(Map<String, Double> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "ResponseAggrItem [key=" + key + ", keyAsString=" + keyAsString + ", docCount=" + docCount + ", values="
				+ values + "]";
	}

}
