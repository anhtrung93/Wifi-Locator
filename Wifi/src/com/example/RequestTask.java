package com.example;

import android.os.AsyncTask;

import com.example.share.Constant;
import com.example.share.Fingerprint;
import com.example.share.Request;

/**
 * 
 * @author anhtrung93
 * 
 *         Class RequestTask is a subclass of AsyncTask. This class is used to
 *         make a new Thread for program to send Request to the server as
 *         Internet actions cannot be done in the main Thread.
 * 
 */

public class RequestTask extends AsyncTask<Request, Void, Fingerprint> {

	/**
	 * @param requests
	 *            the requests[0] will be sent to the server
	 * @return the Fingerprint with a label if sent Request is an instance of
	 *         findRequest; otherwise null
	 */
	protected Fingerprint doInBackground(final Request... requests) {
		Fingerprint responseFingerprint = null;
		try {
			Connection newConnection = new Connection(Constant.SERVER_ADDRESS,
					Constant.SERVER_PORT);
			responseFingerprint = (Fingerprint) newConnection
					.request(requests[0]);
			newConnection.closeSession();
		} catch (Exception taskException) {
			//this is intentionally empty
			//exception will be shown on the main activity
		}
		return responseFingerprint;
	}

}