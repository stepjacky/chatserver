package org.atomsoft.chatserver.example;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class EchoServer {
	private final int port;
	 
	     public EchoServer(int port) {
	        this.port = port;
	     }
	
	      public void run() {
	          // Configure the server.
	         ServerBootstrap bootstrap = new ServerBootstrap(
	                 new NioServerSocketChannelFactory(
	                          Executors.newCachedThreadPool(),
	                         Executors.newCachedThreadPool()));
	 
	         // Set up the pipeline factory.
	         bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
	             public ChannelPipeline getPipeline() throws Exception {
	                 return Channels.pipeline(new EchoServerHandler());
	             }
	         });
  
	         // Bind and start to accept incoming connections.
	          bootstrap.bind(new InetSocketAddress("localhost",port));
	      }
	 
     public static void main(String[] args) throws Exception {
	      new EchoServer(9999).run();
      
     }
}
