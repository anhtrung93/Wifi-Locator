package com.example.test;
import com.example.share.*;

import static org.junit.Assert.*;
import org.junit.*;

public class ConstantTest {
	private Object obj;
	@Before
	public void setUp() throws Exception{
		obj = new Object();
		obj = "quit";
	}
	
	@Test
	public void testContants(){
		assertEquals(Constant.FINISH, "quit");
		assertEquals(Constant.MAXIMUM_DIFFERENCE, 120);
		assertEquals(Constant.SERVER_ADDRESS, "192.168.143.1");
		assertEquals(Constant.SERVER_PORT, 4444);
		assertEquals(Constant.SERVER_FILE, "fp_database.dat");
	}
	
	@Test
	public void testIsFinish(){
		assertEquals(Constant.isFINISH(obj), true);
	}
}
