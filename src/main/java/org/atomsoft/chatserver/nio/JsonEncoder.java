package org.atomsoft.chatserver.nio;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.atomsoft.chatserver.nio.message.Message;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.CorruptedFrameException;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.jboss.netty.util.CharsetUtil;

import com.google.gson.Gson;

public class JsonEncoder extends OneToOneEncoder {

	static final Log logger = LogFactory.getLog(JsonEncoder.class);
	
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {

		
		if(!(msg instanceof Message)) return ChannelBuffers.EMPTY_BUFFER;
		Message message = (Message)msg;
		String json = serialize(message);

		byte[] data = json.getBytes(CharsetUtil.UTF_8);
		int dataLength = data.length;

		logger.info("message: " + json);
		logger.info("message length: " + dataLength);
		ChannelBuffer buf = ChannelBuffers.dynamicBuffer();		
		buf.writeInt(dataLength);
		buf.writeBytes(data);
		return buf;
	}

	private String serialize(Message msg) throws CorruptedFrameException {
		Gson gson = new Gson();
		return gson.toJson(msg);

	}

}
