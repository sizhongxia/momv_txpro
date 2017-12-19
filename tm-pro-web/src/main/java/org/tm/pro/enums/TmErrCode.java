package org.tm.pro.enums;

public enum TmErrCode {
	ServerError(500, "服务器错误"), 
	RequestUnauthorization(401, "未授权访问"), 
	RequestUnauthentication(403, "未登陆");

	private int code;
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private TmErrCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
