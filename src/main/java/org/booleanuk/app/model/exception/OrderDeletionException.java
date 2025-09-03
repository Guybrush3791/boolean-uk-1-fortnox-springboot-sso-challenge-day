package org.booleanuk.app.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderDeletionException extends RuntimeException {
    public OrderDeletionException(String message) {
        super(message);
    }
}