package org.atomsoft.chatserver.nio;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.atomsoft.chatserver.nio.message.Message;
import org.atomsoft.chatserver.nio.message.MessageExecutor;
import org.atomsoft.chatserver.nio.message.MessageProcessorNotRegisteException;
import org.jboss.netty.channel.ChannelHandler.Sharable;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import flex.messaging.io.amf.ASObject;

@Sharable@Component
public class MessageServerHandler extends SimpleChannelUpstreamHandler {

	private static final Log logger = LogFactory
			.getLog(MessageServerHandler.class);

	@Autowired
	private MessageExecutor executor;
	
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		logger.info("收到客户端连接 ! id=  " + e.getChannel().getId());

	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		logger.info("服务端接受到消息" + e.getChannel().getRemoteAddress() + "/");
		Message msg = (Message) e.getMessage();
		
		logger.info("消息类型是:" + msg.getMtype());
		try {
			executor.processMessage(e.getChannel(), msg.getMtype(), msg);
		} catch (MessageProcessorNotRegisteException e1) {
			logger.error(e1);
		}

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		e.getCause().printStackTrace();
		e.getChannel().close();
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		

	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		
	}

}
