package com.example;

/**
 * 
 * @author bvuong93
 * 
 * Request class is a base class 
 *
 */

public abstract class Request {
    protected Fingerprint fingerprint;

    public Request(Fingerprint fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Fingerprint getFingerprint() {
        return this.fingerprint;
    }
}
