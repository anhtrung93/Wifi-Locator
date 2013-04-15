package com.example;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.example.share.*;

import android.os.*;

public class RequestTask extends AsyncTask<Request, Void, Fingerprint> {
	protected Fingerprint doInBackground(Request... requests) {
		Fingerprint response = null;
		try {
			Connection newConnection = new Connection(Constant.SERVER_ADDRESS,
					Constant.SERVER_PORT);
			response = (Fingerprint) newConnection.request(requests[0]);
			newConnection.closeSession();
		} catch (Exception exception) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			exception.printStackTrace(pw);

			// TO DO show exception
		}
		return response;
	}

}
