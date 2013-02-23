package org.atomsoft.chatserver.nio;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.atomsoft.chatserver.nio.message.MessageProcessor;
import org.atomsoft.chatserver.nio.message.MessageProcessorNotRegisteException;
import org.springframework.stereotype.Component;

@Component
public class MessageManager {
	public static final Map<Integer, Object> processorMap = new ConcurrentHashMap<Integer, Object>();
	private static final Log logger = LogFactory.getLog(MessageManager.class);
	public MessageProcessor getProcessor(Integer mtype)
			throws MessageProcessorNotRegisteException {
		MessageProcessor mp = (MessageProcessor) processorMap.get(mtype);
		if (mp == null || !(mp instanceof MessageProcessor))
			throw new MessageProcessorNotRegisteException("消息类型 " + mtype
					+ " 没有注册MessageProcessor 类型的处理器");
		return mp;
	}
    
	/**
	 * 注册一个消息处理器类
	 * 每个消息类型对应一个处理类
	 * @param mtype 消息类型
	 * @param p 处理器bean
	 * */
	public void registe(Integer mtype,Object p){
		if(mtype==null)
			throw new NullPointerException("消息类型不能为整数 ,不能为 null");
		
		processorMap.put(mtype, p);
	    logger.info("消息处理器实例 "+p+" 被注册到处理消息类型:"+mtype);
	}

}
