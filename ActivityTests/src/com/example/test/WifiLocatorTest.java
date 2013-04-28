package com.example.test;

import com.example.WifiLocator;
import com.example.R;
import com.example.share.Fingerprint;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class WifiLocatorTest extends
		ActivityInstrumentationTestCase2<WifiLocator> {
	private WifiLocator activity;
	private Fingerprint currentFingerprint;

	private Button scanButton;
	private CheckBox autoScanButton;
	private ToggleButton onOffButton;
	private Button addNewLabelButton;

	private TextView wifiState;
	private TextView showFingerprintArea;
	private EditText showLocationArea;

	Boolean scanOnClick;

	public WifiLocatorTest() {
		super("com.example", WifiLocator.class);
	}

	public WifiLocatorTest(Class<WifiLocator> name) {
		super(name);
	}

	@Override
	public void setUp() throws Exception {
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
		ViewAsserts.assertOnScreen(wifiState.getRootView(), showLocationArea);
		ViewAsserts.assertOnScreen(wifiState.getRootView(), wifiState);
		ViewAsserts
				.assertOnScreen(wifiState.getRootView(), showFingerprintArea);

		ViewAsserts.assertOnScreen(wifiState.getRootView(), onOffButton);
		ViewAsserts.assertOnScreen(wifiState.getRootView(), scanButton);
		ViewAsserts.assertOnScreen(wifiState.getRootView(), autoScanButton);
		ViewAsserts.assertOnScreen(wifiState.getRootView(), addNewLabelButton);

		ViewAsserts.assertLeftAligned(scanButton, onOffButton);
		ViewAsserts.assertLeftAligned(autoScanButton, showLocationArea);
	}

	public void testInitialize() {
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
