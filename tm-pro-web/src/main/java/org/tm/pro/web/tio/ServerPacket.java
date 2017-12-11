package org.tm.pro.web.tio;

import org.tio.core.intf.Packet;

public class ServerPacket extends Packet {
	private static final long serialVersionUID = 1L;
	public static final int HEADER_LENGHT = 4;// 消息头的长度
	public static final String CHARSET = "utf-8";
	private byte[] body;

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}
}
