// @author: bvuong1993

package com.example.share;

import com.example.*;

// This implements a specific type of a request
// Use this request type to add a fingerprint to a server

public class AddRequest extends Request {
	public AddRequest(Fingerprint fp) {
		super(fp);
	}  
}
