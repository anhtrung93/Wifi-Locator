package com.example.test;
import junit.framework.Assert;
import android.test.*;
import com.example.DataBase;
import com.example.Fingerprint;
import android.test.ActivityInstrumentationTestCase2;
import junit.framework.TestCase;

public class DataBaseTest extends ActivityInstrumentationTestCase2<DataBase> {

	public DataBaseTest(Class<DataBase> name) {
		super(name);
		DataBase dataBase = getActivity();
	}
	
	public DataBaseTest(){
		super("com.example.DataBase", DataBase.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		DataBase dataBase = getActivity();
	}

}
