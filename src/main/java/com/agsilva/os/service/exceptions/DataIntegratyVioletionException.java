package com.agsilva.os.service.exceptions;

public class DataIntegratyVioletionException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DataIntegratyVioletionException(String message) {
        super(message);
    }

    public DataIntegratyVioletionException(String message, Throwable cause) {
        super(message, cause);
    }
}
