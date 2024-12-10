package com.w_farooq_group.user_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String resource, String field, String value){
        super(String.format("%s Already exists with the given %s:%s", resource, field, value));
    }
}
