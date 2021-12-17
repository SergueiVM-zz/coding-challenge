package com.planning.poker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class VoteNotAcceptedException extends RuntimeException {

	public VoteNotAcceptedException(String exception) {
        super(exception);
    }
}
