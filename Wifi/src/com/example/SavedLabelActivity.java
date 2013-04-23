package com.example;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 
 * @author  bravewolf
 * 
 */
@SuppressLint("CutPasteId")
public class SavedLabelActivity extends WifiLocator {
	Button backButton;
	Button saveButton;
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
		this.setUpBackButtonListeners();
		// Add new label
		this.setUpSaveButtonListeners();
	}

	/**
	 * Links xml parts to variables. Allows using variables (button and text
	 * area variables)to control the user interface
	 * 
	 */
	private void setUpInterface() {
		setContentView(R.layout.savedlabel);
		backButton = (Button) findViewById(R.id.back);
		labelShowing = (TextView) findViewById(R.id.labelName);
		saveButton = (Button) findViewById(R.id.saveButton);
		labelAdding = (EditText) findViewById(R.id.addLabelText);
		exceptionConnection = (TextView) findViewById(R.id.exceptConnect);
		exceptionAdding = (TextView) findViewById(R.id.exceptAdd);
	}

	/**
	 * Listens to clicking the backButton and then returns back to the main
	 * activity.
	 */
	private void setUpBackButtonListeners() {
		// Back Listener -> Back to mainActivity
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

	/**
	 * Listens to clicking from saveButton and sends an AddRequest to the
	 * Server.
	 */
	private void setUpSaveButtonListeners() {
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
			}
		});
	}
}
