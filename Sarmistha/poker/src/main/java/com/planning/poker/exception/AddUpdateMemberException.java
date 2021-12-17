package com.planning.poker.exception;

 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.CONFLICT)
public class AddUpdateMemberException extends RuntimeException 
{
    public AddUpdateMemberException(String exception) {
        super(exception);
    }
}