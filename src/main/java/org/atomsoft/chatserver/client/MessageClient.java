package org.atomsoft.chatserver.client;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.atomsoft.chatserver.nio.SystemEnvironment;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class MessageClient {

	private static final Log logger = LogFactory.getLog(MessageClient.class);
	private static final NioClientSocketChannelFactory clientSocketChannelFactory = new NioClientSocketChannelFactory(
			Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
	private static final ClientBootstrap bootstrap = new ClientBootstrap(
			clientSocketChannelFactory);
		
	static {
		bootstrap.setPipelineFactory(new MessageClientPipelineFactory());

		bootstrap.setOption("tcpNoDelay", true);

		bootstrap.setOption("keepAlive", true);
		
		
	}
	public void start() {
     
        // Configure the client.       
        // Set up the event pipeline factory.
        bootstrap.setPipelineFactory(new MessageClientPipelineFactory());
        // Start the connection attempt.
        ChannelFuture future = bootstrap.connect(new InetSocketAddress(SystemEnvironment.HOST, SystemEnvironment.PORT));
        // Wait until the connection is closed or the connection attempt fails.
       
   
       
       
        future.getChannel().getCloseFuture().awaitUninterruptibly();
        // Shut down thread pools to exit.
        bootstrap.releaseExternalResources();
        logger.info("netty client waiting");
	}	
	
	public  static void main(String... args) throws Exception{
		
		
		    new MessageClient().start();
		
	}

}
