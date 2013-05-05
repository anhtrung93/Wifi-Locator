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
	public final static String FINISH = "quit";
	public final static int MAXIMUM_DIFFERENCE = 120; // need changing
	public final static String SERVER_ADDRESS = "10.99.198.197";
	//public static String SERVER_ADDRESS = "10.99.193.144";
	public final static int SERVER_PORT = 4444;
	public final static String SERVER_FILE = "fp_database.dat";

	public static boolean isFINISH(final Object object) {
		boolean result;
		if (object instanceof String) {
			result = ((String) object).equals(FINISH);
		} else {
			result = false;
		}
		return result;
	}
}