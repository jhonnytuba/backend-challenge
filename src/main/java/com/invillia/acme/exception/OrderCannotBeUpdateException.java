package com.invillia.acme.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Order can't be update")
public class OrderCannotBeUpdateException extends RuntimeException {
}
