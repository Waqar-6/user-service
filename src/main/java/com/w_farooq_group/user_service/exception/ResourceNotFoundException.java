package com.w_farooq_group.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resource, String fieldName, String value) {
        super(String.format("%s not found %s:'%s'", resource, fieldName, value));
    }
}
