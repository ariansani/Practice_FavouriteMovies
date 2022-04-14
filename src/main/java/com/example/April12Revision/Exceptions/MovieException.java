package com.example.April12Revision.Exceptions;

public class MovieException extends Exception{
    private String reason;

    public MovieException(String reason) {
        super();
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
