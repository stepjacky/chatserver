package org.atomsoft.chatserver.nio;

public interface MessageType {
	/** 离线 */
	int OFFLINE = 0;

	/** 在线 */
	int ONLINE = 1;
	
	
	/**
	 * 请求聊天事件,带有请求人和被请求人ID
	 * */
	int CHAT_REQUEST = 3;
	
	/**
	 * 聊天请求成功
	 * */
	int CHAT_REQUEST_SUCCESS = 4;
	
	/**
	 * 聊天请求失败
	 **/
	
	int ChAT_REQUEST_FAILURE = 5;
	
	/**
	 * 发送文件
	 * */
	
	int SEND_FILE = 6;
	
	
	/**
	 * 接收文件
	 * */
	int RECEIVED_FILE = 7;
	
	/**
	 * 请求用户树 
	 * */
	
	int GET_USERTREE = 8;
	
	/**
	 * 请求应用信息
	 * */
	
	int GET_APPS = 9;
	
	
	/**
	 * 心跳检测
	 * */
	int HEART_BEAT = 100;

}
