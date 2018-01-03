package org.tm.pro.elastic.model;

import java.util.List;
import java.util.Map;

public class ResponseModel {
	private boolean success;
	private String errMsg;

	private Map<String, List<ResponseAggrItem>> aggrTerms;
	private Map<String, List<ResponseAggrItem>> aggrDateHistograms;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Map<String, List<ResponseAggrItem>> getAggrTerms() {
		return aggrTerms;
	}

	public void setAggrTerms(Map<String, List<ResponseAggrItem>> aggrTerms) {
		this.aggrTerms = aggrTerms;
	}

	public Map<String, List<ResponseAggrItem>> getAggrDateHistograms() {
		return aggrDateHistograms;
	}

	public void setAggrDateHistograms(Map<String, List<ResponseAggrItem>> aggrDateHistograms) {
		this.aggrDateHistograms = aggrDateHistograms;
	}

	@Override
	public String toString() {
		return "ResponseModel [success=" + success + ", errMsg=" + errMsg + ", aggrTerms=" + aggrTerms
				+ ", aggrDateHistograms=" + aggrDateHistograms + "]";
	}

}
