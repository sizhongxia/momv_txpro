package org.tm.pro.picture.result;

import java.io.Serializable;

public class PictureUploadRet implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean ret;
	private SucInfo info;
	private FailInfo error;

	public boolean isRet() {
		return ret;
	}

	public void setRet(boolean ret) {
		this.ret = ret;
	}

	public SucInfo getInfo() {
		return info;
	}

	public void setInfo(SucInfo info) {
		this.info = info;
	}

	public FailInfo getError() {
		return error;
	}

	public void setError(FailInfo error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "PictureUploadRet [ret=" + ret + ", info=" + info + ", error=" + error + "]";
	}

}
