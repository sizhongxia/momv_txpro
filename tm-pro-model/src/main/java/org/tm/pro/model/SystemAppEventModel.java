package org.tm.pro.model;

import java.io.Serializable;

public class SystemAppEventModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String code;
	private String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public SystemAppEventModel() {
		super();
	}

	public SystemAppEventModel(String name, String code, String desc) {
		super();
		this.name = name;
		this.code = code;
		this.desc = desc;
	}

}
