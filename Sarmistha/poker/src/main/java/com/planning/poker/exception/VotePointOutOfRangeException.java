package com.planning.poker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class VotePointOutOfRangeException extends RuntimeException {

	public VotePointOutOfRangeException(String exception) {
        super(exception);
    }
}
