package org.tm.pro.model;

import java.io.Serializable;
import java.util.Date;

public class MessageDataModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date logdate; // 2017/09/02 23:50:01
	private String cache_device_ip; // 117.169.86.139
	private String client_ip; // 117.169.86.139
	private String server_ip; // 117.169.86.139
	private String method; // GET
	private String http_version; // HTTP/1.1
	private String domain; // book.d.ireader.com
	private String uri1; // /group7/M00/5A/B0/CmQUNFlombaEHNwMAAAAAE0KVAE559908403.xml?v=JU18sMnk&t=CmQUNFmqBqk.
	private String ua; // MAUI_WAP_Browser
	private String refer; // NULL
	private String content_type; // text/xml; charset=utf-8
	private String status_code;// 200
	private String cache_stat; // HIT
	private Integer server_port; // 6510
	private Long out_flow; // 47286
	private Date request_begin_time;// 2017/09/02 23:50:01.322
	private Date request_end_time;// 2017/09/02 23:50:01.322
	private Date ttfb;// 2017/09/02 23:50:01.322
	private String url_hash;
	private String cache_type; // 0
	private String back_client_ip; // 117.169.86.139

	private String back_src_server_ip;
	private String back_method;
	private String back_http_version;
	private String back_host;
	private String back_uri;
	private String back_content_type;
	private String back_status_code;

	private Integer back_close_status; // 0
	private String back_cache_ctl; // cache
	private Long back_max_age; // 604800
	private Long back_size; // 47286
	private Integer cache_save; // 1

	private String back_server_port;
	private Long back_flow; // 0
	private Date back_request_begin_time;
	private Date back_request_end_time;
	private String back_ttfb;

	public Date getLogdate() {
		return logdate;
	}

	public void setLogdate(Date logdate) {
		this.logdate = logdate;
	}

	public String getCache_device_ip() {
		return cache_device_ip;
	}

	public void setCache_device_ip(String cache_device_ip) {
		this.cache_device_ip = cache_device_ip;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public String getServer_ip() {
		return server_ip;
	}

	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getHttp_version() {
		return http_version;
	}

	public void setHttp_version(String http_version) {
		this.http_version = http_version;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUri1() {
		return uri1;
	}

	public void setUri1(String uri1) {
		this.uri1 = uri1;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public String getCache_stat() {
		return cache_stat;
	}

	public void setCache_stat(String cache_stat) {
		this.cache_stat = cache_stat;
	}

	public Integer getServer_port() {
		return server_port;
	}

	public void setServer_port(Integer server_port) {
		this.server_port = server_port;
	}

	public Long getOut_flow() {
		return out_flow;
	}

	public void setOut_flow(Long out_flow) {
		this.out_flow = out_flow;
	}

	public Date getRequest_begin_time() {
		return request_begin_time;
	}

	public void setRequest_begin_time(Date request_begin_time) {
		this.request_begin_time = request_begin_time;
	}

	public Date getRequest_end_time() {
		return request_end_time;
	}

	public void setRequest_end_time(Date request_end_time) {
		this.request_end_time = request_end_time;
	}

	public Date getTtfb() {
		return ttfb;
	}

	public void setTtfb(Date ttfb) {
		this.ttfb = ttfb;
	}

	public String getUrl_hash() {
		return url_hash;
	}

	public void setUrl_hash(String url_hash) {
		this.url_hash = url_hash;
	}

	public String getCache_type() {
		return cache_type;
	}

	public void setCache_type(String cache_type) {
		this.cache_type = cache_type;
	}

	public String getBack_client_ip() {
		return back_client_ip;
	}

	public void setBack_client_ip(String back_client_ip) {
		this.back_client_ip = back_client_ip;
	}

	public String getBack_src_server_ip() {
		return back_src_server_ip;
	}

	public void setBack_src_server_ip(String back_src_server_ip) {
		this.back_src_server_ip = back_src_server_ip;
	}

	public String getBack_method() {
		return back_method;
	}

	public void setBack_method(String back_method) {
		this.back_method = back_method;
	}

	public String getBack_http_version() {
		return back_http_version;
	}

	public void setBack_http_version(String back_http_version) {
		this.back_http_version = back_http_version;
	}

	public String getBack_host() {
		return back_host;
	}

	public void setBack_host(String back_host) {
		this.back_host = back_host;
	}

	public String getBack_uri() {
		return back_uri;
	}

	public void setBack_uri(String back_uri) {
		this.back_uri = back_uri;
	}

	public String getBack_content_type() {
		return back_content_type;
	}

	public void setBack_content_type(String back_content_type) {
		this.back_content_type = back_content_type;
	}

	public String getBack_status_code() {
		return back_status_code;
	}

	public void setBack_status_code(String back_status_code) {
		this.back_status_code = back_status_code;
	}

	public Integer getBack_close_status() {
		return back_close_status;
	}

	public void setBack_close_status(Integer back_close_status) {
		this.back_close_status = back_close_status;
	}

	public String getBack_cache_ctl() {
		return back_cache_ctl;
	}

	public void setBack_cache_ctl(String back_cache_ctl) {
		this.back_cache_ctl = back_cache_ctl;
	}

	public Long getBack_max_age() {
		return back_max_age;
	}

	public void setBack_max_age(Long back_max_age) {
		this.back_max_age = back_max_age;
	}

	public Long getBack_size() {
		return back_size;
	}

	public void setBack_size(Long back_size) {
		this.back_size = back_size;
	}

	public Integer getCache_save() {
		return cache_save;
	}

	public void setCache_save(Integer cache_save) {
		this.cache_save = cache_save;
	}

	public String getBack_server_port() {
		return back_server_port;
	}

	public void setBack_server_port(String back_server_port) {
		this.back_server_port = back_server_port;
	}

	public Long getBack_flow() {
		return back_flow;
	}

	public void setBack_flow(Long back_flow) {
		this.back_flow = back_flow;
	}

	public Date getBack_request_begin_time() {
		return back_request_begin_time;
	}

	public void setBack_request_begin_time(Date back_request_begin_time) {
		this.back_request_begin_time = back_request_begin_time;
	}

	public Date getBack_request_end_time() {
		return back_request_end_time;
	}

	public void setBack_request_end_time(Date back_request_end_time) {
		this.back_request_end_time = back_request_end_time;
	}

	public String getBack_ttfb() {
		return back_ttfb;
	}

	public void setBack_ttfb(String back_ttfb) {
		this.back_ttfb = back_ttfb;
	}

}
