package org.atomsoft.chatserver.nio;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class  MessageServer{
	
	@Autowired
	private MessageServerPipelineFactory myPipeline;
	private static final Log logger = LogFactory.getLog(MessageServer.class);
	
	public void start() throws Exception {
		// Configure the server.
				ServerBootstrap bootstrap = new ServerBootstrap(
						new NioServerSocketChannelFactory(
								Executors.newCachedThreadPool(),
								Executors.newCachedThreadPool()));

				// Set up the default event pipeline.
				bootstrap.setPipelineFactory(myPipeline);
				// 设置相关参数
				bootstrap.setOption("child.tcpNoDelay", true);
				// 设置相关参数
				bootstrap.setOption("child.keepAlive", true);
				
				// 绑定相关端口
				// Bind and start to accept incoming connections.
				bootstrap.bind(new InetSocketAddress(SystemEnvironment.HOST,SystemEnvironment.PORT));
				
				logger.info("netty 聊天服务启动完毕.监听 "+SystemEnvironment.HOST+":"+SystemEnvironment.PORT+"端口....");
				
				logger.info("准备接受消息...");

	}
	
}
