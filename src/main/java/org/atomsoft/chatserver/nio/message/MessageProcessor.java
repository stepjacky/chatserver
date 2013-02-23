package org.atomsoft.chatserver.nio.message;

import org.jboss.netty.channel.Channel;

public interface MessageProcessor {
     public void process(Channel channel,Integer mtype,Message object);
}
