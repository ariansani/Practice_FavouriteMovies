package com.example.April12Revision.Exceptions;

public class ActorException extends Exception{
    private String reason;

    public ActorException(String reason) {
        super();
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
