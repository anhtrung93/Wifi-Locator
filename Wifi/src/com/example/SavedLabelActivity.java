package com.example;

import com.example.share.Constant;

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
	EditText labelAdd;
	TextView label;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Setup Interface
		this.setUpInterface();
		// Setup all ButtonListenrs
		this.setUpButtonListeners();
		//Add new label
		this.addLabel();
		

		// TODO Auto-generated method stub
	}
	
	//To setup interface
	private void setUpInterface(){
		setContentView(R.layout.savedlabel);
		back = (Button) findViewById(R.id.back);
		label = (TextView) findViewById(R.id.labelName);
		save = (Button) findViewById(R.id.saveButton);
		labelAdd = (EditText) findViewById(R.id.addLabelText);
		
		
	}

	// To setup all ButtonListenrs
	private void setUpButtonListeners(){
		//Back Listener -> Back to mainActivity
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});

	}
	
	
	//Add new label for fingerprint
	private void addLabel() {
		//Save listener -> save new label from <edittext>labelAdd
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
				String name = labelAdd.getText().toString();
				WifiLocator.generalFingerprint.addLabel(name);
				label.setText(WifiLocator.generalFingerprint.getLabel());
				Database newData = new Database("192.168.43.23", Constant.SERVER_PORT);
				newData.add(WifiLocator.generalFingerprint);
				} catch (Exception e) {}
				
			}
		});
		//StringBuilder status = new StringBuilder();
		
//		int a = WifiLocator.generalFingerprint.getSize();
		
//		if (WifiLocator.generalFingerprint.getSize() == 0){
//			status.append("No WiFi connection!!!");
//		} else {
//			status.append(WifiLocator.generalFingerprint.toString());
//		}
		
//		WifiLocator.generalFingerprint.addLabel("a");
//		status.append(WifiLocator.generalFingerprint.getLabel());

	

		//label.setText(status);
	}

}

