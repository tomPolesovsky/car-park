package cz.pa165.carpark.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason="Unauthorized access")
public class LoginException extends RuntimeException {

}