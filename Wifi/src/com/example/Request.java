// bvuong1993
package com.example;
public abstract class Request {
    protected Fingerprint fp;
    public Request(Fingerprint fp) {
	this.fp = fp;
    }
    public Fingerprint getFingerprint() {
	return this.fp;
    }
}