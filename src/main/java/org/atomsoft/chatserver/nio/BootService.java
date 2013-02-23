package org.atomsoft.chatserver.nio;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.atomsoft.chatserver.annotation.MessageKey;
import org.atomsoft.chatserver.nio.message.MessageProcessor;
import org.jackysoft.common.config.CommonConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BootService {
	private static final Log logger = LogFactory.getLog(BootService.class);
	private static AnnotationConfigApplicationContext applicationContext;
	
	static{
		logger.info("初始化数据服务...");
		applicationContext = new AnnotationConfigApplicationContext(CommonConfiguration.class);
		logger.info("数据服务启动完毕.");
	    MessageServer mserver = applicationContext.getBean(MessageServer.class);
	    try {
			mserver.start();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	private BootService(){}
	public static void bootSevice(){
		BootService bs = new BootService();
	    bs.registeMessageProcessor();
	}
	
	
	/**
	 * 注册消息处理器
	 * */
	public void registeMessageProcessor(){
		MessageManager manager = applicationContext.getBean(MessageManager.class);
		Map<String,Object> processor = applicationContext.getBeansWithAnnotation(MessageKey.class);
	    for(Object p : processor.values()){
	    	if(!(p instanceof MessageProcessor))
	    		continue;
	    	MessageKey manno = p.getClass().getAnnotation(MessageKey.class);
	        manager.registe(Integer.valueOf(manno.mtype()), p);
	    }
	}
	
	
	public static void main(String... args){
		BootService.bootSevice();
	}
	
	
}
