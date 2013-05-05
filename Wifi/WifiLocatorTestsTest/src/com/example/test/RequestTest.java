package com.example.test;

import static org.junit.Assert.*;
import com.example.share.*;

import org.junit.*;

public class RequestTest {
	private Fingerprint fingerprint;
	private WifiSignature wifiSignature1, wifiSignature2, wifiSignature3, wifiSignature4, wifiSignature5;
	private WifiSignature[] wifiList;
	private Request request;
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() throws Exception{
		/*
		 * Initialize a fingerprint from a wifiList of 5 wifiSignatures
		 */
		
		wifiSignature1 = new WifiSignature("a1:b2:c3:d4:e5", -60);
		
		wifiSignature2 = new WifiSignature("f3:a2:b1:c3", 0);
		
		wifiSignature3 = new WifiSignature("a2:b1:c3:d4:e5", -60);
		
		wifiSignature4 = new WifiSignature("f3:a2:b1:c3", 0);
		
		wifiSignature5 = new WifiSignature("a1:b2:c3:d4:e5", -60);
		
		wifiList = new WifiSignature[] {wifiSignature1, wifiSignature2, wifiSignature3, wifiSignature4, wifiSignature5};
		
		fingerprint = new Fingerprint(wifiList);
		
		request = new Request(fingerprint) {
		};
	}
	
	@Test
	public void testserialVersionUID(){
		/*
		 * Test the static variable SerialVersionUID
		 */
		assertEquals(Request.serialVersionUID, 1L);
	}
	
	@Test
	public void testGetFingerprint(){
		/*
		 * Test the initialization
		 */
		assertEquals(fingerprint, request.getFingerprint());
	}
}

