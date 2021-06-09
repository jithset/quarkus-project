package io.github.jithinsethu.hibernatevalidation.utils.handlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class RestCustomException extends Exception implements Serializable {

    @Getter @Setter
    private int status;

    @Getter @Setter
    private String message;

    public RestCustomException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public RestCustomException(String message, int status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public RestCustomException(String message, Throwable cause, int status, String message1) {
        super(message, cause);
        this.status = status;
        this.message = message1;
    }
}
