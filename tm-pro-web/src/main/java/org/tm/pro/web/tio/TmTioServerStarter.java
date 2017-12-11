package org.tm.pro.web.tio;

import org.springframework.beans.factory.InitializingBean;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.server.AioServer;
import org.tio.server.ServerGroupContext;
import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;

public class TmTioServerStarter implements InitializingBean {
	// handler, 包括编码、解码、消息处理
	public static ServerAioHandler aioHandler = new TmServerAioHandler();
	// 事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
	public static ServerAioListener aioListener = new ServerAioListener() {

		@Override
		public void onBeforeClose(ChannelContext arg0, Throwable arg1, String arg2, boolean arg3) {
			System.out.println("onBeforeClose");
		}

		@Override
		public void onAfterSent(ChannelContext arg0, Packet arg1, boolean arg2) throws Exception {
			System.out.println("onAfterSent");
		}

		@Override
		public void onAfterReceived(ChannelContext arg0, Packet arg1, int arg2) throws Exception {
			System.out.println("onAfterReceived");
		}

		@Override
		public void onAfterConnected(ChannelContext arg0, boolean arg1, boolean arg2) throws Exception {
			System.out.println("onAfterConnected");
		}

		@Override
		public void onAfterClose(ChannelContext arg0, Throwable arg1, String arg2, boolean arg3) throws Exception {
			System.out.println("onAfterClose");
		}
	};
	// 一组连接共用的上下文对象
	public static ServerGroupContext serverGroupContext = new ServerGroupContext(aioHandler, aioListener);
	// aioServer对象
	public static AioServer aioServer = new AioServer(serverGroupContext);
	// 有时候需要绑定ip，不需要则null
	public static String serverIp = null;
	// 监听的端口
	public static int serverPort = 6789;

	@Override
	public void afterPropertiesSet() throws Exception {
		serverGroupContext.setHeartbeatTimeout(5000);
		aioServer.start(serverIp, serverPort);
	}

	public static void main(String[] args) throws Exception {
		serverGroupContext.setHeartbeatTimeout(5000);
		aioServer.start(serverIp, serverPort);
	}
}
