// @author: bvuong1993                                                                   

package com.example;

// This implements a specific type of a request
// Use this request type to find a fingerprint in a server

public class FindRequest extends Request {
    public FindRequest(Fingerprint fp) {
        super(fp);
    }
}
