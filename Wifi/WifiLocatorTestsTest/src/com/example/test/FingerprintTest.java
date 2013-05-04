package com.example.test;

import static org.junit.Assert.*;// Maybe you will have a question here, why using Junit to test?
import org.junit.*;// The answer is just because the WifiSignature class do not use anything invoke to Android :D 

import java.util.Arrays;
import com.example.share.*;

/**
 * @author hidrodo (^^)
 * 
 * This is simply the tests of FingerPrint class
 *
 */

public class FingerprintTest {
	private Fingerprint fingerprint1, fingerprint2, fingerprint3, fingerprint4, fingerprint5;
	private WifiSignature wifiSignature1, wifiSignature2, wifiSignature3, wifiSignature4, wifiSignature5;
	private WifiSignature[] wifiList;
	
	@Before
	public void setUp() throws Exception{
		wifiSignature1 = new WifiSignature("a1:b2:c3:d4:e5", -60);
		
		wifiSignature2 = new WifiSignature("f3:a2:b1:c3", 0);
		
		wifiSignature3 = new WifiSignature("a2:b1:c3:d4:e5", -60);
		
		wifiSignature4 = new WifiSignature("f3:a2:b1:c3", 0);
		
		wifiSignature5 = new WifiSignature("a1:b2:c3:d4:e5", -60);
		
		wifiList = new WifiSignature[] {wifiSignature1};
		fingerprint1 = new Fingerprint(wifiList);
		
		wifiList = new WifiSignature[] {wifiSignature1, wifiSignature2};
		fingerprint2 = new Fingerprint(wifiList);
		
		wifiList = new WifiSignature[] {wifiSignature1, wifiSignature2, wifiSignature3};
		fingerprint3 = new Fingerprint(wifiList);
		
		wifiList = new WifiSignature[] {wifiSignature1, wifiSignature2, wifiSignature3, wifiSignature4};
		fingerprint4 = new Fingerprint(wifiList);
		
		wifiList = new WifiSignature[] {wifiSignature1, wifiSignature2, wifiSignature3, wifiSignature4, wifiSignature5};
		fingerprint5 = new Fingerprint(wifiList);
	}
	
	@Test
	public void testGetWifiList1() {
		assertTrue(Arrays.equals(fingerprint1.getWifiList(), new WifiSignature[] {wifiSignature1}));
	}
	
	@Test
	public void testGetWifiList2() {
		assertTrue(Arrays.equals(fingerprint2.getWifiList(), new WifiSignature[] {wifiSignature1, wifiSignature2}));
	}
	
	@Test
	public void testGetWifiList3() {
		assertTrue(Arrays.equals(fingerprint3.getWifiList(), new WifiSignature[] {wifiSignature1, wifiSignature2, wifiSignature3}));
	}

	@Test
	public void testGetWifiList4() {
		assertTrue(Arrays.equals(fingerprint4.getWifiList(), new WifiSignature[] {wifiSignature1, wifiSignature2, wifiSignature3, wifiSignature4}));
	}
	
	@Test
	public void testGetWifiList5() {
		assertTrue(Arrays.equals(fingerprint5.getWifiList(), new WifiSignature[] {wifiSignature1, wifiSignature2, wifiSignature3, wifiSignature4, wifiSignature5}));
	}
	
	@Test
	public void testSize1(){
		assertTrue(1 == fingerprint1.getSize());
	}
	
	@Test
	public void testSize2(){
		assertTrue(2 == fingerprint2.getSize());
	}
	
	@Test
	public void testSize3(){
		assertTrue(3 == fingerprint3.getSize());
	}
	
	@Test
	public void testSize4(){
		assertTrue(4 == fingerprint4.getSize());
	}
	
	@Test
	public void testSize5(){
		assertTrue(5 == fingerprint5.getSize());
	}
	
	@Test
	public void testSort1(){
		fingerprint1.sort();
		assertTrue(Arrays.equals(fingerprint1.getWifiList(), new WifiSignature[] {wifiSignature1}));
	}
	
	@Test
	public void testSort2(){
		fingerprint2.sort();
		assertTrue(Arrays.equals(fingerprint2.getWifiList(), new WifiSignature[] {wifiSignature1, wifiSignature2}));
	}
	
	@Test
	public void testSort3(){
		fingerprint3.sort();
		assertTrue(Arrays.equals(fingerprint3.getWifiList(), new WifiSignature[] {wifiSignature1, wifiSignature3, wifiSignature2}));
	}
	
	@Test
	public void testSort4(){
		fingerprint4.sort();
		assertTrue(Arrays.equals(fingerprint4.getWifiList(), new WifiSignature[] {wifiSignature1, wifiSignature3, wifiSignature2, wifiSignature4}));
	}
	
	@Test
	public void testSort5(){
		fingerprint5.sort();
		assertTrue(Arrays.equals(fingerprint5.getWifiList(), new WifiSignature[] {wifiSignature1, wifiSignature5, wifiSignature3, wifiSignature2, wifiSignature4}));
	}
	
	@Test
	public void testdifferFrom1(){
		assertTrue(fingerprint1.differFrom(fingerprint2) == 0.0);
	}
	
	@Test
	public void testdifferFrom2(){
		assertTrue(fingerprint1.differFrom(fingerprint3) == 0.0);
	}
	
	@Test
	public void testdifferFrom3(){
		assertTrue(fingerprint1.differFrom(fingerprint4) == 0.0);
	}
	
	@Test
	public void testdifferFrom4(){
		assertTrue(fingerprint1.differFrom(fingerprint5) == 0.0);
	}
}
