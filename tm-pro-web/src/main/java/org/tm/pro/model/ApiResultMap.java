package org.tm.pro.model;

import java.io.Serializable;
import java.util.List;

public class ApiResultMap implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean status;
	private int code;
	private String msg;
	private Object data;
	private List<?> list;
	private int totalPage;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public ApiResultMap() {
		super();
	}

	public ApiResultMap(int code, String msg) {
		super();
		this.status = false;
		this.code = code;
		this.msg = msg;
	}

	public ApiResultMap(String msg) {
		super();
		this.status = true;
		this.code = 200;
		this.msg = msg;
	}

	public ApiResultMap(Object data) {
		super();
		this.status = true;
		this.code = 200;
		this.data = data;
	}

	public ApiResultMap(List<Object> list) {
		super();
		this.list = list;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
