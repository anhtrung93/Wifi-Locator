package com.example.test;
import com.example.share.*;

import static org.junit.Assert.*;
import org.junit.*;



public class WifiSignatureTest {
	private WifiSignature wS1, wS2, wS3, wS4, wS5, wS6, wS7;
	
	@Before
	public void setUp() throws Exception{
		wS1 = new WifiSignature("a1:b2:c3:d4:e5", -60);
		
		wS2 = new WifiSignature("f3:a2:b1:c3", 0);
		
		wS3 = new WifiSignature("a2:b1:c3:d4:e5", -60);
		
		wS4 = new WifiSignature("f3:a2:b1:c3", 0);
		
		wS5 = new WifiSignature("a1:b2:c3:d4:e5", -60);
		
		wS6 = new WifiSignature("xxx", -26, "303G2");
		
		wS7 = new WifiSignature("xxx", -26, "303G2");
	}
	
	@Test
	public void testBSSID1(){
		//check the BSSID of the WifiSignature;
		assertEquals("a1:b2:c3:d4:e5", wS1.getBasicServiceSetIdentifier());
	}
	
	@Test
	public void testBSSID2(){
		assertEquals("f3:a2:b1:c3", wS2.getBasicServiceSetIdentifier());
	}
	
	@Test
	public void testBSSID3(){
		assertEquals("a2:b1:c3:d4:e5", wS3.getBasicServiceSetIdentifier());
	}
	
	@Test
	public void testBSSID4(){
		assertEquals("f3:a2:b1:c3", wS4.getBasicServiceSetIdentifier());
	}
	
	@Test
	public void testBSSID5(){
		assertEquals("a1:b2:c3:d4:e5", wS5.getBasicServiceSetIdentifier());
	}
	
	@Test
	public void testBSSID6(){
		assertEquals("xxx", wS6.getBasicServiceSetIdentifier());
	}
	
	@Test
	public void testBSSID7(){
		assertEquals("xxx", wS7.getBasicServiceSetIdentifier());
	}
	
	@Test
	public void testRSS1(){
		//check the RSS of the WifiSignature;
		assertEquals(-60, wS1.getReceivedSignalStrength());
	}
	
	@Test
	public void testRSS2(){
		assertEquals(0, wS2.getReceivedSignalStrength());
	}
	
	@Test
	public void testRSS3(){
		assertEquals(-60, wS3.getReceivedSignalStrength());
	}
	
	@Test
	public void testRSS4(){
		assertEquals(0, wS4.getReceivedSignalStrength());
	}
	
	@Test
	public void testRSS5(){
		assertEquals(-60, wS5.getReceivedSignalStrength());
	}
	
	@Test
	public void testRSS6(){
		assertEquals(-26, wS6.getReceivedSignalStrength());
	}
	
	@Test
	public void testRSS7(){
		assertEquals(-26, wS7.getReceivedSignalStrength());
	}
	
	@Test
	public void testCompareTo1(){
		// check method compareTo of the WifiSignature;
		assertEquals(true, wS1.compareTo(wS1) == 0);
		assertEquals(false, wS1.compareTo(wS2) == 0);
		assertEquals(false, wS1.compareTo(wS3) == 0);
		assertEquals(false, wS1.compareTo(wS4) == 0);
		assertEquals(true, wS1.compareTo(wS5) == 0);
		assertEquals(false, wS1.compareTo(wS6) == 0);
		assertEquals(false, wS1.compareTo(wS7) == 0);
	}
	
	@Test
	public void testCompareTo2(){
		assertEquals(false, wS2.compareTo(wS1) == 0);
		assertEquals(true, wS2.compareTo(wS2) == 0);
		assertEquals(false, wS2.compareTo(wS3) == 0);
		assertEquals(true, wS2.compareTo(wS4) == 0);
		assertEquals(false, wS2.compareTo(wS5) == 0);
		assertEquals(false, wS2.compareTo(wS6) == 0);
		assertEquals(false, wS2.compareTo(wS7) == 0);
	}

	@Test
	public void testCompareTo3(){
		assertEquals(false, wS3.compareTo(wS1) == 0);
		assertEquals(false, wS3.compareTo(wS2) == 0);
		assertEquals(true, wS3.compareTo(wS3) == 0);
		assertEquals(false, wS3.compareTo(wS4) == 0);
		assertEquals(false, wS3.compareTo(wS5) == 0);
		assertEquals(false, wS3.compareTo(wS6) == 0);
		assertEquals(false, wS3.compareTo(wS7) == 0);
	}
	
	@Test
	public void testCompareTo4(){
		assertEquals(false, wS4.compareTo(wS1) == 0);
		assertEquals(true, wS4.compareTo(wS2) == 0);
		assertEquals(false, wS4.compareTo(wS3) == 0);
		assertEquals(true, wS4.compareTo(wS4) == 0);
		assertEquals(false, wS4.compareTo(wS5) == 0);
		assertEquals(false, wS4.compareTo(wS6) == 0);
		assertEquals(false, wS4.compareTo(wS7) == 0);
	}

	@Test
	public void testCompareTo5(){
		assertEquals(true, wS5.compareTo(wS1) == 0);
		assertEquals(false, wS5.compareTo(wS2) == 0);
		assertEquals(false, wS5.compareTo(wS3) == 0);
		assertEquals(false, wS5.compareTo(wS4) == 0);
		assertEquals(true, wS5.compareTo(wS5) == 0);
		assertEquals(false, wS5.compareTo(wS6) == 0);
		assertEquals(false, wS5.compareTo(wS7) == 0);
	}
	
	@Test
	public void testCompareTo6(){
		assertEquals(false, wS6.compareTo(wS1) == 0);
		assertEquals(false, wS6.compareTo(wS2) == 0);
		assertEquals(false, wS6.compareTo(wS3) == 0);
		assertEquals(false, wS6.compareTo(wS4) == 0);
		assertEquals(false, wS6.compareTo(wS5) == 0);
		assertEquals(true, wS6.compareTo(wS7) == 0);
	}
	
	@Test
	public void testCompareTo7(){
		assertEquals(false, wS7.compareTo(wS1) == 0);
		assertEquals(false, wS7.compareTo(wS2) == 0);
		assertEquals(false, wS7.compareTo(wS3) == 0);
		assertEquals(false, wS7.compareTo(wS4) == 0);
		assertEquals(false, wS7.compareTo(wS5) == 0);
		assertEquals(true, wS7.compareTo(wS6) == 0);
	}
}
