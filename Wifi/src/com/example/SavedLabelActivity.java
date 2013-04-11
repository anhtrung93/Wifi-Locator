package com.example;

import com.example.share.Constant;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("CutPasteId")
public class SavedLabelActivity extends WifiLocator {
	Button back;
	Button save;
	EditText labelAdding;
	TextView labelShowing;
	TextView exceptionConnection;
	TextView exceptionAdding;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Setup Interface
		this.setUpInterface();
		// Setup all ButtonListenrs
		this.setUpButtonListeners();
		// Add new label
		this.addLabel();
		// TODO Auto-generated method stub
	}

	// To setup Interface
	private void setUpInterface() {
		setContentView(R.layout.savedlabel);
		back = (Button) findViewById(R.id.back);
		labelShowing = (TextView) findViewById(R.id.labelName);
		save = (Button) findViewById(R.id.saveButton);
		labelAdding = (EditText) findViewById(R.id.addLabelText);
		exceptionConnection = (TextView) findViewById(R.id.exceptConnect);
		exceptionAdding = (TextView) findViewById(R.id.exceptAdd);
	}

	// To setup some ButtonListenrs
	private void setUpButtonListeners() {
		// Back Listener -> Back to mainActivity
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

	// Add new label for fingerprint
	private void addLabel() {

		/**
		 * Save listener -> save new label for a fingerprint from
		 * <edittext>labelAdd After that, send that fingerprint to server
		 */

		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String tempName = labelAdding.getText().toString();
				WifiLocator.generalFingerprint.addLabel(tempName);
				labelShowing.setText(WifiLocator.generalFingerprint.getLabel());

				Database newData = null;
				// Make new Database to connect server
				try {
					newData = new Database("192.168.9.103",
							Constant.SERVER_PORT);
				} catch (Exception e) {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					e.printStackTrace(pw);
					exceptionConnection.setText(sw.toString());
				}
				try {
					newData.add(WifiLocator.generalFingerprint);
				} catch (Exception e) {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					e.printStackTrace(pw);
					exceptionAdding.setText(sw.toString());
				}

			}
		});
	}
}
