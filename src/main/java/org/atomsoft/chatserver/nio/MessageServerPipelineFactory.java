package org.atomsoft.chatserver.nio;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.logging.LoggingHandler;
import org.jboss.netty.handler.timeout.ReadTimeoutHandler;
import org.jboss.netty.handler.timeout.WriteTimeoutHandler;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageServerPipelineFactory implements ChannelPipelineFactory {
	
	@Autowired
	private MessageServerHandler messageHandler;
	
	private  Timer timer = new  HashedWheelTimer();
	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = pipeline();

		pipeline.addLast("logger", new LoggingHandler());
		// 处理coder
		pipeline.addLast("decoder", new JsonDecoder());
		pipeline.addLast("encoder", new JsonEncoder());
		//	
		pipeline.addLast("handler", messageHandler);
		pipeline.addLast("readTimeout", new ReadTimeoutHandler(this.timer,20 * 60));

		pipeline.addLast("writeTimeout", new WriteTimeoutHandler(this.timer,20 * 60));
		return pipeline;
	}

}
