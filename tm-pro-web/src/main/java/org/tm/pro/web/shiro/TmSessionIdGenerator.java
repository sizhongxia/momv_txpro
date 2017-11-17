package org.tm.pro.web.shiro;

import java.io.Serializable;
import java.util.UUID;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.tm.pro.utils.TmShaUtil;

public class TmSessionIdGenerator implements SessionIdGenerator {
	/**
	 * 自定义SessionID生成
	 */
	@Override
	public Serializable generateId(Session session) {
		return TmShaUtil.sha1(UUID.randomUUID().toString());
	}
}