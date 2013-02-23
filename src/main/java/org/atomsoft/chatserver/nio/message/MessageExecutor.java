package org.atomsoft.chatserver.nio.message;

import org.atomsoft.chatserver.nio.MessageManager;
import org.jboss.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import flex.messaging.io.amf.ASObject;

@Component
public class MessageExecutor {
	@Autowired
	private MessageManager messageManager;
    public void processMessage(Channel channel,Integer mtype,Message object) throws MessageProcessorNotRegisteException{
    	MessageProcessor processor = messageManager.getProcessor(mtype);	
    	processor.process(channel, mtype, object);
    }
}
