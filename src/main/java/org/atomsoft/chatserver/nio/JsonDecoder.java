package org.atomsoft.chatserver.nio;

import java.io.StringReader;

import org.atomsoft.chatserver.nio.message.Message;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.CorruptedFrameException;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

import com.google.gson.Gson;

public class JsonDecoder extends FrameDecoder {

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {
		//wait until the length prefix is available (in bytes)
        if (buffer.readableBytes() < 4){
                return null;
        }

        buffer.markReaderIndex();

        //wait until the whole data is available
        int dataLength = buffer.readInt();
        if (buffer.readableBytes() < dataLength){
                buffer.resetReaderIndex();
                return null;
        }

        byte[] decoded = new byte[dataLength];
        buffer.readBytes(decoded);

        return deserialize(decoded);
	}

	private Object deserialize(byte[] buf) throws CorruptedFrameException {
        
       Gson gson = new Gson();
       StringReader reader = new StringReader(new String(buf));
       
       Message mbody = gson.fromJson(reader, Message.class);
       
       return mbody;     

}
}
