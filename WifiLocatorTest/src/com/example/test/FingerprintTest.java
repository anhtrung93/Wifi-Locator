package com.example.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.*;

import com.example.Fingerprint;
import com.example.WiFiSignature;

public class FingerprintTest {
	private Fingerprint f1, f2, f3, f4, f5;
	private WiFiSignature wS1, wS2, wS3, wS4, wS5;
	private WiFiSignature[] wifiList;
	
	@Before
	public void setUp() throws Exception{
		wS1 = new WiFiSignature("a1:b2:c3:d4:e5", -60);
		
		wS2 = new WiFiSignature("f3:a2:b1:c3", 0);
		
		wS3 = new WiFiSignature("a2:b1:c3:d4:e5", -60);
		
		wS4 = new WiFiSignature("f3:a2:b1:c3", 0);
		
		wS5 = new WiFiSignature("a1:b2:c3:d4:e5", -60);
		
		wifiList = new WiFiSignature[] {wS1};
		f1 = new Fingerprint(wifiList);
		
		wifiList = new WiFiSignature[] {wS1, wS2};
		f2 = new Fingerprint(wifiList);
		
		wifiList = new WiFiSignature[] {wS1, wS2, wS3};
		f3 = new Fingerprint(wifiList);
		
		wifiList = new WiFiSignature[] {wS1, wS2, wS3, wS4};
		f4 = new Fingerprint(wifiList);
		
		wifiList = new WiFiSignature[] {wS1, wS2, wS3, wS4, wS5};
		f5 = new Fingerprint(wifiList);
	}
	
	@Test
	public void testGetWifiList1() {
		assertTrue(Arrays.equals(f1.getWiFiList(), new WiFiSignature[] {wS1}));
	}
	
	@Test
	public void testGetWifiList2() {
		assertTrue(Arrays.equals(f2.getWiFiList(), new WiFiSignature[] {wS1, wS2}));
	}
	
	@Test
	public void testGetWifiList3() {
		assertTrue(Arrays.equals(f3.getWiFiList(), new WiFiSignature[] {wS1, wS2, wS3}));
	}

	@Test
	public void testGetWifiList4() {
		assertTrue(Arrays.equals(f4.getWiFiList(), new WiFiSignature[] {wS1, wS2, wS3, wS4}));
	}
	
	@Test
	public void testGetWifiList5() {
		assertTrue(Arrays.equals(f5.getWiFiList(), new WiFiSignature[] {wS1, wS2, wS3, wS4, wS5}));
	}
	
	@Test
	public void testSize1(){
		assertTrue(1 == f1.getSize());
	}
	
	@Test
	public void testSize2(){
		assertTrue(2 == f2.getSize());
	}
	
	@Test
	public void testSize3(){
		assertTrue(3 == f3.getSize());
	}
	
	@Test
	public void testSize4(){
		assertTrue(4 == f4.getSize());
	}
	
	@Test
	public void testSize5(){
		assertTrue(5 == f5.getSize());
	}
	
	@Test
	public void testSort1(){
		f1.sort();
		assertTrue(Arrays.equals(f1.getWiFiList(), new WiFiSignature[] {wS1}));
	}
	
	@Test
	public void testSort2(){
		f2.sort();
		assertTrue(Arrays.equals(f2.getWiFiList(), new WiFiSignature[] {wS1, wS2}));
	}
	
	@Test
	public void testSort3(){
		f3.sort();
		assertTrue(Arrays.equals(f3.getWiFiList(), new WiFiSignature[] {wS1, wS3, wS2}));
	}
	
	@Test
	public void testSort4(){
		f4.sort();
		assertTrue(Arrays.equals(f4.getWiFiList(), new WiFiSignature[] {wS1, wS3, wS2, wS4}));
	}
	
	@Test
	public void testSort5(){
		f5.sort();
		assertTrue(Arrays.equals(f5.getWiFiList(), new WiFiSignature[] {wS1, wS5, wS3, wS2, wS4}));
	}
	
	@Test
	public void testdifferFrom1(){
	
	}
}
