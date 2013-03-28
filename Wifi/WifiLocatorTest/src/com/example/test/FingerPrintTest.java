package com.example.test;

import com.example.*;

import junit.framework.TestCase;

public class FingerPrintTest extends TestCase {
	Fingerprint finger;
	private WiFiSignature sign1, sign2, sign3;
	private String label;
	private WiFiSignature[] wiFiList;
	public FingerPrintTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		sign1 = new WiFiSignature("b", -50);
		sign2 = new WiFiSignature("c", -60);
		sign3 = new WiFiSignature("a", -70);
		label = new String("G2_Building");
		wiFiList = new WiFiSignature[] {sign1, sign2, sign3};
	}
	
	public void testSort(){
		finger = new Fingerprint(wiFiList);
		assertEquals(finger.toString(),sign1.toString() + sign2.toString() + sign3.toString());
		finger.sort();
		assertEquals(finger.toString(),sign3.toString() + sign1.toString() + sign2.toString());
	}

}
