package com.example.test;
import com.example.share.*;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author hidrodo (^^)
 * 
 * This is simply the tests of WifiSignature class
 *
 */

public class WifiSignatureTest {
	/*
	 * We're going to test with 7 wifiSignature variables
	 */
	private WifiSignature wifiSignature1;
	private WifiSignature wifiSignature2;
	private WifiSignature wifiSignature3;
	private WifiSignature wifiSignature4;
	private WifiSignature wifiSignature5;
	private WifiSignature wifiSignature6;
	private WifiSignature wifiSignature7;
	
	@Before
	public void setUp() throws Exception{
		/*
		 * Initialization 7 above wifiSignature variables 
		 * 5 first variables are initialized by the constructor setting by 2 parameters: the basicServiceSetIdentifier and 
		 * the receivedSignalStrength
		 * 2 next variables are initialized by the constructor setting by 2 parameters: the basicServiceSetIdentifier, 
		 * the receivedSignalStrength and the serviceSetIdentifier 
		 */
		wifiSignature1 = new WifiSignature("a1:b2:c3:d4:e5", -60);
		
		wifiSignature2 = new WifiSignature("f3:a2:b1:c3", 0);
		
		wifiSignature3 = new WifiSignature("a2:b1:c3:d4:e5", -60);
		
		wifiSignature4 = new WifiSignature("f3:a2:b1:c3", 0);
		
		wifiSignature5 = new WifiSignature("a1:b2:c3:d4:e5", -60);
		
		wifiSignature6 = new WifiSignature("xxx", -26, "303G2");
		
		wifiSignature7 = new WifiSignature("xxx", -26, "303G2");
	}
	
	@Test
	public void testBasicServiceSetIdentifier(){
		//test the basicServiceSetIdentifier of 7 wifiSignature variables;
		assertEquals("a1:b2:c3:d4:e5", wifiSignature1.getBasicServiceSetIdentifier());
		
		assertEquals("f3:a2:b1:c3", wifiSignature2.getBasicServiceSetIdentifier());
		
		assertEquals("a2:b1:c3:d4:e5", wifiSignature3.getBasicServiceSetIdentifier());
		
		assertEquals("f3:a2:b1:c3", wifiSignature4.getBasicServiceSetIdentifier());
		
		assertEquals("a1:b2:c3:d4:e5", wifiSignature5.getBasicServiceSetIdentifier());
		
		assertEquals("xxx", wifiSignature6.getBasicServiceSetIdentifier());
		
		assertEquals("xxx", wifiSignature7.getBasicServiceSetIdentifier());
	}
	
	@Test
	public void testreceivedSignalStrength(){
		//test the receivedSignalStrength of the 7 WifiSignature variables;
		assertEquals(-60, wifiSignature1.getReceivedSignalStrength());
		
		assertEquals(0, wifiSignature2.getReceivedSignalStrength());
		
		assertEquals(-60, wifiSignature3.getReceivedSignalStrength());
		
		assertEquals(0, wifiSignature4.getReceivedSignalStrength());
		
		assertEquals(-60, wifiSignature5.getReceivedSignalStrength());
		
		assertEquals(-26, wifiSignature6.getReceivedSignalStrength());
		
		assertEquals(-26, wifiSignature7.getReceivedSignalStrength());
	}
	
	@Test
	public void testCompareTo(){
		// check method compareTo of the WifiSignature class;
		assertEquals(true, wifiSignature1.compareTo(wifiSignature1) == 0);
		assertEquals(false, wifiSignature1.compareTo(wifiSignature2) == 0);
		assertEquals(false, wifiSignature1.compareTo(wifiSignature3) == 0);
		assertEquals(false, wifiSignature1.compareTo(wifiSignature4) == 0);
		assertEquals(true, wifiSignature1.compareTo(wifiSignature5) == 0);
		assertEquals(false, wifiSignature1.compareTo(wifiSignature6) == 0);
		assertEquals(false, wifiSignature1.compareTo(wifiSignature7) == 0);
		
		assertEquals(true, wifiSignature2.compareTo(wifiSignature2) == 0);
		assertEquals(false, wifiSignature2.compareTo(wifiSignature3) == 0);
		assertEquals(true, wifiSignature2.compareTo(wifiSignature4) == 0);
		assertEquals(false, wifiSignature2.compareTo(wifiSignature5) == 0);
		assertEquals(false, wifiSignature2.compareTo(wifiSignature6) == 0);
		assertEquals(false, wifiSignature2.compareTo(wifiSignature7) == 0);
		
		assertEquals(true, wifiSignature3.compareTo(wifiSignature3) == 0);
		assertEquals(false, wifiSignature3.compareTo(wifiSignature4) == 0);
		assertEquals(false, wifiSignature3.compareTo(wifiSignature5) == 0);
		assertEquals(false, wifiSignature3.compareTo(wifiSignature6) == 0);
		assertEquals(false, wifiSignature3.compareTo(wifiSignature7) == 0);
		
		assertEquals(true, wifiSignature4.compareTo(wifiSignature4) == 0);
		assertEquals(false, wifiSignature4.compareTo(wifiSignature5) == 0);
		assertEquals(false, wifiSignature4.compareTo(wifiSignature6) == 0);
		assertEquals(false, wifiSignature4.compareTo(wifiSignature7) == 0);
		
		assertEquals(true, wifiSignature5.compareTo(wifiSignature5) == 0);
		assertEquals(false, wifiSignature5.compareTo(wifiSignature6) == 0);
		assertEquals(false, wifiSignature5.compareTo(wifiSignature7) == 0);
		
		assertEquals(true, wifiSignature6.compareTo(wifiSignature6) == 0);
		assertEquals(true, wifiSignature6.compareTo(wifiSignature7) == 0);
		
		assertEquals(true, wifiSignature7.compareTo(wifiSignature7) == 0);
	}

}
