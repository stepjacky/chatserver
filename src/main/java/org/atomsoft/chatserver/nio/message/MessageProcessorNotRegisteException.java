package org.atomsoft.chatserver.nio.message;

public class MessageProcessorNotRegisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9071870555283265751L;

	public MessageProcessorNotRegisteException(String msg){
		super(msg);
	}
}
