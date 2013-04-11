package com.example.share;

/**
 * 
 * @author bvuong93
 * 
 *         Constant Class is created to hold shared constants which nearly all
 *         classes use.
 * 
 */

public class Constant {
	public static String FINISH = "quit";
	public static int MAXIMUM_DIFFERENCE = 100; // need changing
	public static String SERVER_ADDRESS = "localhost";
	public static int SERVER_PORT = 4444;

	public static boolean isFINISH(Object object) {
		if (object instanceof String)
			return ((String) object).equals(FINISH);
		else
			return false;
	}
}
