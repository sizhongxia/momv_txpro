package org.tm.pro.model;

import java.io.Serializable;

public class SystemPermissionWordModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String group;
	private String name;
	private String code;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

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

	public SystemPermissionWordModel() {
		super();
	}

	public SystemPermissionWordModel(String group, String name, String code) {
		super();
		this.group = group;
		this.name = name;
		this.code = code;
	}

}
