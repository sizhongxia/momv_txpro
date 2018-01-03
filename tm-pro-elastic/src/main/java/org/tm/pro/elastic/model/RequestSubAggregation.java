package org.tm.pro.elastic.model;

public class RequestSubAggregation {
	private String field;
	private AggregationEnum type;
	private String format;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public AggregationEnum getType() {
		return type;
	}

	public void setType(AggregationEnum type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
