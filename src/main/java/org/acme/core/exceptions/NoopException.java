package org.acme.core.exceptions;

public class NoopException extends RuntimeException {

    @Override
    public String getMessage() {
        return "noop";
    }

}