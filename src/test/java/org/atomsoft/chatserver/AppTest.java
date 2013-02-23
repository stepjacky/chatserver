package org.atomsoft.chatserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.atomsoft.chatserver.client.MessageClient;
import org.atomsoft.chatserver.nio.BootService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
	private static final Log logger = LogFactory.getLog(AppTest.class);
	
	
	
	@Before
	public void before(){
		
	}
	
	@Test
    public void testApp() throws Exception
    {  

    }
	
	@Test@Ignore
	public void clientAPp() throws Exception{
		BootService.bootSevice();
	}
	
	
    
}
