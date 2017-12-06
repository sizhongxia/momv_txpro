package org.tm.pro.es.entity;

import java.util.List;

public class EsSearchResult {
	private List<EsSearchItem> datas;

	public List<EsSearchItem> getDatas() {
		return datas;
	}

	public void setDatas(List<EsSearchItem> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "EsSearchResult [datas=" + datas + "]";
	}

}
