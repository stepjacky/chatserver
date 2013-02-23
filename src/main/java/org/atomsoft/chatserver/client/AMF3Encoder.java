package org.atomsoft.chatserver.client;

import java.io.ByteArrayOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Output;


public class AMF3Encoder extends LengthFieldPrepender {

	Log logger = LogFactory.getLog(AMF3Encoder.class);

	public AMF3Encoder() {
		super(4);
		
	}

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		SerializationContext serializationContext = new SerializationContext();
		Amf3Output amf3Output = new Amf3Output(serializationContext);
		amf3Output.setOutputStream(stream);
		amf3Output.writeObject(msg);
		byte[] objSe = stream.toByteArray();
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		if (objSe != null && objSe.length > 0) {
			buffer.writeInt(objSe.length);
			buffer.writeBytes(objSe);
			logger.info("已发送编码数据长度: "+objSe.length);
			logger.info("已发送编码数据: "+msg);
			return buffer;
		}
		
		buffer.writeInt(0);
		buffer.writeBytes(new byte[0]);
		return ChannelBuffers.EMPTY_BUFFER;
	}

}
