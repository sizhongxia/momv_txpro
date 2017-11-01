package org.tm.pro.picture.zimg.model;

import java.io.Serializable;

public class ZimgSucInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String md5;
	private Long size;

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ZimgSucInfo [md5=" + md5 + ", size=" + size + "]";
	}

}
