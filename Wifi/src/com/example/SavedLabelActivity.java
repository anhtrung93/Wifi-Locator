package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SavedLabelActivity extends Activity {
	Button back;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		this.setUpInterface(); // Setup Interface
		this.setUpButtonListeners();




		// TODO Auto-generated method stub
	}
	//To setup interface
	private void setUpInterface(){
		setContentView(R.layout.savedlabel);
		back = (Button) findViewById(R.id.back);
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

}

