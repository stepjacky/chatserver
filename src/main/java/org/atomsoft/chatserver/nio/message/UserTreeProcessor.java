package org.atomsoft.chatserver.nio.message;

import org.atomsoft.chatserver.annotation.MessageKey;
import org.atomsoft.chatserver.nio.MessageType;
import org.jboss.netty.channel.Channel;
import org.springframework.stereotype.Component;

@Component
@MessageKey(mtype=MessageType.GET_USERTREE)
public class UserTreeProcessor extends AbstractMessageProcessor {

	@Override
	public void process(Channel channel, Integer mtype, Message object) {
		// TODO Auto-generated method stub

	}

}
