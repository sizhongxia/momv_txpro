package org.tm.pro.es.model;

import java.io.Serializable;

public class ElasticHits<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private long total;
	private double max_score;
	private ElasticHitsItem<T>[] hits;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public double getMax_score() {
		return max_score;
	}

	public void setMax_score(double max_score) {
		this.max_score = max_score;
	}

	public ElasticHitsItem<T>[] getHits() {
		return hits;
	}

	public void setHits(ElasticHitsItem<T>[] hits) {
		this.hits = hits;
	}

}
