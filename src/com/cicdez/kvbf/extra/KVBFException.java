package com.cicdez.kvbf.extra;

import java.io.IOException;

public class KVBFException extends IOException {
    public KVBFException() {
        super();
    }
    public KVBFException(String message) {
        super(message);
    }
    public KVBFException(String message, Throwable cause) {
        super(message, cause);
    }
    public KVBFException(Throwable cause) {
        super(cause);
    }
}
