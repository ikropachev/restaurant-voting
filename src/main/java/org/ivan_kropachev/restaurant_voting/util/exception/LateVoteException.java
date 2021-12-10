package org.ivan_kropachev.restaurant_voting.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "You can't vote after 11:00 am.")
public class LateVoteException extends RuntimeException {
    public LateVoteException(String message) {
        super(message);
    }
}
