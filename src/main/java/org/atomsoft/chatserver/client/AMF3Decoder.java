package org.atomsoft.chatserver.client;

import java.io.ByteArrayInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Input;


public class AMF3Decoder extends LengthFieldBasedFrameDecoder {
	public static final Log logger = LogFactory.getLog(AMF3Decoder.class);
    	
	/**
	 * 
	 * @param maxFrameLength
	 *            包的最大大小
	 * @param lengthFieldOffset
	 *            包头信息，长度的偏移位
	 * @param lengthFieldLength
	 *            包头信息，长度位数
	 */
	public AMF3Decoder(int maxFrameLength, int lengthFieldOffset,
			int lengthFieldLength) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
	}

	/**
	 * 
	 * @param maxFrameLength
	 */
	public AMF3Decoder(int maxFrameLength) {
		super(maxFrameLength, 0, 4, 0, 4);
	}

	
	public AMF3Decoder(){
		super(Integer.MAX_VALUE, 0, 4, 0, 4);
	}
	/**
	  * 
	  */
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {
		
	    logger.info("正在解码消息...");
	    // Make sure if the length field was received.
	     if (buffer.readableBytes() < 4) {
	        // The length field was not received yet - return null.
	        // This method will be invoked again when more packets are
	        // received and appended to the buffer.
	        return null;
	     }
	     // The length field is in the buffer.

	     // Mark the current buffer position before reading the length field
	     // because the whole frame might not be in the buffer yet.
	     // We will reset the buffer position to the marked position if
	     // there's not enough bytes in the buffer.
	     buffer.markReaderIndex();

	     // Read the length field.
	     int length = buffer.readInt();

	     // Make sure if there's enough bytes in the buffer.
	     if (buffer.readableBytes() < length) {
	        // The whole bytes were not received yet - return null.
	        // This method will be invoked again when more packets are
	        // received and appended to the buffer.

	        // Reset to the marked position to read the length field again
	        // next time.
	        buffer.resetReaderIndex();

	        return null;
	     }	    
	    // There's enough bytes in the buffer. Read it.
	    ChannelBuffer frame = buffer.readBytes(length);
        byte[] content = new byte[length];
		frame.readBytes(content);
		SerializationContext serializationContext = new SerializationContext();
		Amf3Input amf3Input = new Amf3Input(serializationContext);
		amf3Input.setInputStream(new ByteArrayInputStream(content));
		Object message = amf3Input.readObject();
		logger.info("解码后的消息是"+message);
		return message;
	}
}
