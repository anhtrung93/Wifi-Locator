package com.example.test;

import static org.junit.Assert.*;
import com.example.share.*;

import org.junit.*;

public class RequestTest {
	private Fingerprint fingerprint;
	private WifiSignature wS1, wS2, wS3, wS4, wS5;
	private WifiSignature[] wifiList;
	private Request request;
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() throws Exception{
		wS1 = new WifiSignature("a1:b2:c3:d4:e5", -60);
		
		wS2 = new WifiSignature("f3:a2:b1:c3", 0);
		
		wS3 = new WifiSignature("a2:b1:c3:d4:e5", -60);
		
		wS4 = new WifiSignature("f3:a2:b1:c3", 0);
		
		wS5 = new WifiSignature("a1:b2:c3:d4:e5", -60);
		
		wifiList = new WifiSignature[] {wS1, wS2, wS3, wS4, wS5};
		fingerprint = new Fingerprint(wifiList);
		
		request = new Request(fingerprint) {
		};
	}
	
	@Test
	public void testserialVersionUID(){
		assertEquals(Request.serialVersionUID, 1L);
	}
	
	@Test
	public void testGetFingerprint(){
		assertEquals(fingerprint, request.getFingerprint());
	}
}

