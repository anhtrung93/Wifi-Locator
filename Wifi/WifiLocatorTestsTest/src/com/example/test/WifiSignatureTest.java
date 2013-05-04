package com.example.test;
import com.example.share.*;

import static org.junit.Assert.*;
import org.junit.*;



public class WifiSignatureTest {
	private WifiSignature wifiSignature1, wifiSignature2, wifiSignature3, wifiSignature4, wifiSignature5, wifiSignature6, wifiSignature7;
	
	@Before
	public void setUp() throws Exception{
		wifiSignature1 = new WifiSignature("a1:b2:c3:d4:e5", -60);
		
		wifiSignature2 = new WifiSignature("f3:a2:b1:c3", 0);
		
		wifiSignature3 = new WifiSignature("a2:b1:c3:d4:e5", -60);
		
		wifiSignature4 = new WifiSignature("f3:a2:b1:c3", 0);
		
		wifiSignature5 = new WifiSignature("a1:b2:c3:d4:e5", -60);
		
		wifiSignature6 = new WifiSignature("xxx", -26, "303G2");
		
		wifiSignature7 = new WifiSignature("xxx", -26, "303G2");
	}
	
	@Test
	public void testbasicServiceSetIdentifier1(){
		//check the basicServiceSetIdentifier of the WifiSignature;
		assertEquals("a1:b2:c3:d4:e5", wifiSignature1.getBasicServiceSetIdentifier());
	}
	
	@Test
	public void testbasicServiceSetIdentifier2(){
		assertEquals("f3:a2:b1:c3", wifiSignature2.getBasicServiceSetIdentifier());
	}
	
	@Test
	public void testbasicServiceSetIdentifier3(){
		assertEquals("a2:b1:c3:d4:e5", wifiSignature3.getBasicServiceSetIdentifier());
	}
	
	@Test
	public void testbasicServiceSetIdentifier4(){
		assertEquals("f3:a2:b1:c3", wifiSignature4.getBasicServiceSetIdentifier());
	}
	
	@Test
	public void testbasicServiceSetIdentifier5(){
		assertEquals("a1:b2:c3:d4:e5", wifiSignature5.getBasicServiceSetIdentifier());
	}
	
	@Test
	public void testbasicServiceSetIdentifier6(){
		assertEquals("xxx", wifiSignature6.getBasicServiceSetIdentifier());
	}
	
	@Test
	public void testbasicServiceSetIdentifier7(){
		assertEquals("xxx", wifiSignature7.getBasicServiceSetIdentifier());
	}
	
	@Test
	public void testreceivedSignalStrength1(){
		//check the receivedSignalStrength of the WifiSignature;
		assertEquals(-60, wifiSignature1.getReceivedSignalStrength());
	}
	
	@Test
	public void testreceivedSignalStrength2(){
		assertEquals(0, wifiSignature2.getReceivedSignalStrength());
	}
	
	@Test
	public void testreceivedSignalStrength3(){
		assertEquals(-60, wifiSignature3.getReceivedSignalStrength());
	}
	
	@Test
	public void testreceivedSignalStrength4(){
		assertEquals(0, wifiSignature4.getReceivedSignalStrength());
	}
	
	@Test
	public void testreceivedSignalStrength5(){
		assertEquals(-60, wifiSignature5.getReceivedSignalStrength());
	}
	
	@Test
	public void testreceivedSignalStrength6(){
		assertEquals(-26, wifiSignature6.getReceivedSignalStrength());
	}
	
	@Test
	public void testreceivedSignalStrength7(){
		assertEquals(-26, wifiSignature7.getReceivedSignalStrength());
	}
	
	@Test
	public void testCompareTo1(){
		// check method compareTo of the WifiSignature;
		assertEquals(true, wifiSignature1.compareTo(wifiSignature1) == 0);
		assertEquals(false, wifiSignature1.compareTo(wifiSignature2) == 0);
		assertEquals(false, wifiSignature1.compareTo(wifiSignature3) == 0);
		assertEquals(false, wifiSignature1.compareTo(wifiSignature4) == 0);
		assertEquals(true, wifiSignature1.compareTo(wifiSignature5) == 0);
		assertEquals(false, wifiSignature1.compareTo(wifiSignature6) == 0);
		assertEquals(false, wifiSignature1.compareTo(wifiSignature7) == 0);
	}
	
	@Test
	public void testCompareTo2(){
		assertEquals(false, wifiSignature2.compareTo(wifiSignature1) == 0);
		assertEquals(true, wifiSignature2.compareTo(wifiSignature2) == 0);
		assertEquals(false, wifiSignature2.compareTo(wifiSignature3) == 0);
		assertEquals(true, wifiSignature2.compareTo(wifiSignature4) == 0);
		assertEquals(false, wifiSignature2.compareTo(wifiSignature5) == 0);
		assertEquals(false, wifiSignature2.compareTo(wifiSignature6) == 0);
		assertEquals(false, wifiSignature2.compareTo(wifiSignature7) == 0);
	}

	@Test
	public void testCompareTo3(){
		assertEquals(false, wifiSignature3.compareTo(wifiSignature1) == 0);
		assertEquals(false, wifiSignature3.compareTo(wifiSignature2) == 0);
		assertEquals(true, wifiSignature3.compareTo(wifiSignature3) == 0);
		assertEquals(false, wifiSignature3.compareTo(wifiSignature4) == 0);
		assertEquals(false, wifiSignature3.compareTo(wifiSignature5) == 0);
		assertEquals(false, wifiSignature3.compareTo(wifiSignature6) == 0);
		assertEquals(false, wifiSignature3.compareTo(wifiSignature7) == 0);
	}
	
	@Test
	public void testCompareTo4(){
		assertEquals(false, wifiSignature4.compareTo(wifiSignature1) == 0);
		assertEquals(true, wifiSignature4.compareTo(wifiSignature2) == 0);
		assertEquals(false, wifiSignature4.compareTo(wifiSignature3) == 0);
		assertEquals(true, wifiSignature4.compareTo(wifiSignature4) == 0);
		assertEquals(false, wifiSignature4.compareTo(wifiSignature5) == 0);
		assertEquals(false, wifiSignature4.compareTo(wifiSignature6) == 0);
		assertEquals(false, wifiSignature4.compareTo(wifiSignature7) == 0);
	}

	@Test
	public void testCompareTo5(){
		assertEquals(true, wifiSignature5.compareTo(wifiSignature1) == 0);
		assertEquals(false, wifiSignature5.compareTo(wifiSignature2) == 0);
		assertEquals(false, wifiSignature5.compareTo(wifiSignature3) == 0);
		assertEquals(false, wifiSignature5.compareTo(wifiSignature4) == 0);
		assertEquals(true, wifiSignature5.compareTo(wifiSignature5) == 0);
		assertEquals(false, wifiSignature5.compareTo(wifiSignature6) == 0);
		assertEquals(false, wifiSignature5.compareTo(wifiSignature7) == 0);
	}
	
	@Test
	public void testCompareTo6(){
		assertEquals(false, wifiSignature6.compareTo(wifiSignature1) == 0);
		assertEquals(false, wifiSignature6.compareTo(wifiSignature2) == 0);
		assertEquals(false, wifiSignature6.compareTo(wifiSignature3) == 0);
		assertEquals(false, wifiSignature6.compareTo(wifiSignature4) == 0);
		assertEquals(false, wifiSignature6.compareTo(wifiSignature5) == 0);
		assertEquals(true, wifiSignature6.compareTo(wifiSignature7) == 0);
	}
	
	@Test
	public void testCompareTo7(){
		assertEquals(false, wifiSignature7.compareTo(wifiSignature1) == 0);
		assertEquals(false, wifiSignature7.compareTo(wifiSignature2) == 0);
		assertEquals(false, wifiSignature7.compareTo(wifiSignature3) == 0);
		assertEquals(false, wifiSignature7.compareTo(wifiSignature4) == 0);
		assertEquals(false, wifiSignature7.compareTo(wifiSignature5) == 0);
		assertEquals(true, wifiSignature7.compareTo(wifiSignature6) == 0);
	}
}
