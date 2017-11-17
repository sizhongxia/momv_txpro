package org.tm.pro.es.entity;

import java.io.Serializable;

public class ZxUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer age;
	private String birthday;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
