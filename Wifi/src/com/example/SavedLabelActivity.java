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
	TextView ecep;
	TextView ecep2;
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
		ecep = (TextView) findViewById(R.id.ecep);
		ecep2 = (TextView) findViewById(R.id.ecep2);
		
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
				String name = labelAdd.getText().toString();
				WifiLocator.generalFingerprint.addLabel(name);
				label.setText(WifiLocator.generalFingerprint.getLabel());
				Database newData=null;
				
				try {
				newData = new Database("192.168.9.103", Constant.SERVER_PORT);
				
				} catch (Exception e) {
					ecep.setText("sai me may roi");
				}
				try {
					
					newData.add(WifiLocator.generalFingerprint);
					} catch (Exception e) {
						ecep2.setText("ji");
					}
				
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

