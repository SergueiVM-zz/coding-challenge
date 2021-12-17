package com.planning.poker.exception;

 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SessionExpireException extends RuntimeException 
{
    public SessionExpireException(String exception) {
        super(exception);
    }
}