package org.tm.pro.es.entity;

import java.math.BigDecimal;

public class EsSearchItem {
	private String name;
	private BigDecimal value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "EsSearchItem [name=" + name + ", value=" + value + "]";
	}

}
