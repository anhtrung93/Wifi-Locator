package com.example.test;

import com.example.WifiLocator;
import com.example.R;

import android.annotation.TargetApi;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
/**
 * @author anhtrung93 and comments by hidrodo
 * 
 * This is the Android Activity tests of WifiLocator class
 *
 */
public class ActivityTest extends ActivityInstrumentationTestCase2<WifiLocator> {
	
	private WifiLocator activity;

	private Button scanButton;
	private CheckBox autoScanButton;
	private ToggleButton onOffButton;
	private Button addNewLabelButton;

	private TextView wifiState;
	private TextView showFingerprintArea;
	private EditText showLocationArea;

	Boolean scanOnClick;

	/**
	 * Called when the activity is first created. 
	 * Test the interface.
	 * Test the Registers receivers. 
	 * Test the buttons initialization.
	 * Test the listeners for addNewLabelButton, onOffButton, scanButton and autoScanButton.
	 * 
	 */
	
	@SuppressWarnings("deprecation")
	public ActivityTest() {
		super("com.example", WifiLocator.class);
	}

	public ActivityTest(Class<WifiLocator> name) {
		super(name);
	}

	@Override
	public void setUp() throws Exception {
		/*
		 * Initialize activity to test
		 */
		super.setUp();
		activity = getActivity();

		showLocationArea = (EditText) activity.findViewById(R.id.addLabelText);
		wifiState = (TextView) activity.findViewById(R.id.wifistate);
		showFingerprintArea = (TextView) activity.findViewById(R.id.textStatus);

		onOffButton = (ToggleButton) activity.findViewById(R.id.onoff);
		scanButton = (Button) activity.findViewById(R.id.scan);
		autoScanButton = (CheckBox) activity.findViewById(R.id.autoscan);
		addNewLabelButton = (Button) activity.findViewById(R.id.saveButton);
	}

	public void testBeCreated() {
		/*
		 * Test the creation of showLocationArea, wifiState, showFingerprintArea, onOffButton, scanButton, autoScanButton, 
		 * addNewLabelButton
		 */
		assertNotNull(activity);

		assertNotNull(showLocationArea);
		assertNotNull(wifiState);
		assertNotNull(showFingerprintArea);

		assertNotNull(onOffButton);
		assertNotNull(scanButton);
		assertNotNull(autoScanButton);
		assertNotNull(addNewLabelButton);
	}

	public void testView() {
		/*
		 * Test the viewing on the screen of showLocationArea, wifiState, showFingerprintArea, onOffButton, scanButton, 
		 * autoScanButton, addNewLabelButton
		 */
		ViewAsserts.assertOnScreen(wifiState.getRootView(), showLocationArea);
		ViewAsserts.assertOnScreen(wifiState.getRootView(), wifiState);
		ViewAsserts.assertOnScreen(wifiState.getRootView(), showFingerprintArea);
		ViewAsserts.assertOnScreen(wifiState.getRootView(), onOffButton);
		ViewAsserts.assertOnScreen(wifiState.getRootView(), scanButton);
		ViewAsserts.assertOnScreen(wifiState.getRootView(), autoScanButton);
		ViewAsserts.assertOnScreen(wifiState.getRootView(), addNewLabelButton);

		ViewAsserts.assertLeftAligned(scanButton, onOffButton);
		ViewAsserts.assertLeftAligned(autoScanButton, showLocationArea);
	}
	@TargetApi(15)
	public void testClickable() {
		/*
		 * Test The Clickable of onOffButton, scanButton, autoScanButton and the addNewLabelButton 
		 */
		assertTrue(onOffButton.isClickable());
		assertTrue(onOffButton.callOnClick());

		assertTrue(scanButton.isClickable());
		assertTrue(scanButton.callOnClick());

		assertFalse(autoScanButton.isChecked());
		assertTrue(autoScanButton.isClickable());
		assertTrue(autoScanButton.callOnClick());

		assertFalse(addNewLabelButton.isClickable());
	}

	public void tearDown() throws Exception {
		super.tearDown();
	}

}
