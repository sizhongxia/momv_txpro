package org.tm.pro.elastic.model;

import java.util.Set;

public class RequestModel {
	private String index;
	private String type;

	private int from;
	private int size;

	// 缓存服务器地址
	private String cacheDeviceIp;
	// 区域城市
	private String ipipCity;
	// 接入方式
	private String accessMode;
	// 域名
	private String domain;

	private String qmTimeField;
	private String qmTimeStart;
	private String qmTimeEnd;

	private String[] aggrTerms;

	private Set<RequestDateHistogram> dateHistograms;

	private String searchJson;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getCacheDeviceIp() {
		return cacheDeviceIp;
	}

	public void setCacheDeviceIp(String cacheDeviceIp) {
		this.cacheDeviceIp = cacheDeviceIp;
	}

	public String getIpipCity() {
		return ipipCity;
	}

	public void setIpipCity(String ipipCity) {
		this.ipipCity = ipipCity;
	}

	public String getAccessMode() {
		return accessMode;
	}

	public void setAccessMode(String accessMode) {
		this.accessMode = accessMode;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getQmTimeField() {
		return qmTimeField;
	}

	public void setQmTimeField(String qmTimeField) {
		this.qmTimeField = qmTimeField;
	}

	public String getQmTimeStart() {
		return qmTimeStart;
	}

	public void setQmTimeStart(String qmTimeStart) {
		this.qmTimeStart = qmTimeStart;
	}

	public String getQmTimeEnd() {
		return qmTimeEnd;
	}

	public void setQmTimeEnd(String qmTimeEnd) {
		this.qmTimeEnd = qmTimeEnd;
	}

	public String[] getAggrTerms() {
		return aggrTerms;
	}

	public void setAggrTerms(String[] aggrTerms) {
		this.aggrTerms = aggrTerms;
	}

	public Set<RequestDateHistogram> getDateHistograms() {
		return dateHistograms;
	}

	public void setDateHistograms(Set<RequestDateHistogram> dateHistograms) {
		this.dateHistograms = dateHistograms;
	}

	public String getSearchJson() {
		return searchJson;
	}

	public void setSearchJson(String searchJson) {
		this.searchJson = searchJson;
	}

}
