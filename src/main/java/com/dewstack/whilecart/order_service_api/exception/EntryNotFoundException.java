package com.dewstack.whilecart.order_service_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntryNotFoundException extends RuntimeException {
    public EntryNotFoundException (String message) {
        super(message);
    }
}
