package org.tm.pro.es.model;

import java.io.Serializable;

public class ElasticResponseModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private long took;
	private boolean timed_out;
	private ElasticShards _shards;
	private ElasticHits<T> hits;

	public long getTook() {
		return took;
	}

	public void setTook(long took) {
		this.took = took;
	}

	public boolean isTimed_out() {
		return timed_out;
	}

	public void setTimed_out(boolean timed_out) {
		this.timed_out = timed_out;
	}

	public ElasticShards get_shards() {
		return _shards;
	}

	public void set_shards(ElasticShards _shards) {
		this._shards = _shards;
	}

	public ElasticHits<T> getHits() {
		return hits;
	}

	public void setHits(ElasticHits<T> hits) {
		this.hits = hits;
	}
}
