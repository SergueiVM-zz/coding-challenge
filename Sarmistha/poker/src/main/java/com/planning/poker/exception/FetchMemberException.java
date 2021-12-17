package com.planning.poker.exception;

 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FetchMemberException extends RuntimeException 
{
    public FetchMemberException(String exception) {
        super(exception);
    }
}