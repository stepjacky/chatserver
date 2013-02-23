package org.atomsoft.chatserver.client;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;

public class MessageClientPipelineFactory implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		  ChannelPipeline pipeline = pipeline();
		  
	        pipeline.addLast("decoder", new AMF3Decoder());
	        pipeline.addLast("encoder", new AMF3Encoder());
	        pipeline.addLast("handler", new MessageClientHandler());
	 
	        return pipeline;
	}

}
