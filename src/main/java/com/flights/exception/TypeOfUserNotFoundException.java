package com.flights.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "TypeOfUser not found!")
public class TypeOfUserNotFoundException extends Exception {
}
