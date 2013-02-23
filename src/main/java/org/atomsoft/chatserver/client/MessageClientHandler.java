package org.atomsoft.chatserver.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.entity.User;
import org.jboss.netty.channel.ChannelHandler.Sharable;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

@Sharable
public class MessageClientHandler extends SimpleChannelUpstreamHandler{

	  private static final Log logger = LogFactory.getLog(MessageClientHandler.class);
	 
	  private int i=0;
	    @Override
	    public void channelConnected(
	            ChannelHandlerContext ctx, ChannelStateEvent e) {
	       logger.info("客户端已连接! id=  "+e.getChannel().getId());
           User user = new User();
           user.setMtype(2);
           user.setUsername("qujiakang");
           user.setPassword("123456");	      
	       e.getChannel().write(user);
	       logger.info("客户端已发消息!");
	    }
	 
	    @Override
	    public void messageReceived(
	            ChannelHandlerContext ctx, MessageEvent e) {
	      
	    }
	 
	    @Override
	    public void exceptionCaught(
	            ChannelHandlerContext ctx, ExceptionEvent e) {
	        // Close the connection when an exception is raised.
	        logger.error(e.getCause());
	        e.getCause().printStackTrace();
	        e.getChannel().close();
	    }

}
