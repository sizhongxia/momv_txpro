package org.tm.pro.picture.zimg.model;

import java.io.Serializable;

public class ZimgUploadRet implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean ret;
	private ZimgSucInfo info;
	private ZimgFailInfo error;

	public boolean isRet() {
		return ret;
	}

	public void setRet(boolean ret) {
		this.ret = ret;
	}

	public ZimgSucInfo getInfo() {
		return info;
	}

	public void setInfo(ZimgSucInfo info) {
		this.info = info;
	}

	public ZimgFailInfo getError() {
		return error;
	}

	public void setError(ZimgFailInfo error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ZimgUploadRet [ret=" + ret + ", info=" + info + ", error=" + error + "]";
	}

}
