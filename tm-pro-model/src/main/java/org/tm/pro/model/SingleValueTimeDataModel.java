package org.tm.pro.model;

import java.io.Serializable;

public class SingleValueTimeDataModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String time;
	private Object value;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
