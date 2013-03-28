package com.example.test;

import junit.framework.TestCase;
import com.example.*;

public class WifiSignatureTest extends TestCase {
	private WiFiSignature wiFiSignature;
	public WifiSignatureTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		wiFiSignature = new WiFiSignature("abcxyz", -50);
	}

	public void testGetInstance(){
		assertEquals(wiFiSignature.getRSS(),-50);
		assertEquals(wiFiSignature.getBSSID(), "abcxyz");
	}
}
