package com.example.test;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.example.share.Constant;

/**
 * @author hidrodo (^^)
 * 
 * This is simply the tests of Constant class
 *
 */

public class ConstantTest {
	private Object object;
	
	@Before
	public void setUp() throws Exception{
		// Initialize an object
		object = new Object();
		object = "quit";
	}
	
	@Test
	public void testContants(){
		// Test the constants
		assertEquals(Constant.FINISH, "quit");
		assertEquals(Constant.MAXIMUM_DIFFERENCE, 120);
		assertEquals(Constant.SERVER_ADDRESS, "192.168.143.1");
		assertEquals(Constant.SERVER_PORT, 4444);
		assertEquals(Constant.SERVER_FILE, "fp_database.dat");
	}
	
	@Test
	public void testIsFinish(){
		// Test the isFinish method of Constant class
		assertEquals(Constant.isFINISH(object), true);
	}
}
