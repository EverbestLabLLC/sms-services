package com.everbestlab.sms_services.eskiz.exceptions;

public class EskizServerException extends RuntimeException {
    public EskizServerException(Throwable cause) {
        super(cause);
    }

    public EskizServerException(String message) {
        super(message);
    }
}
