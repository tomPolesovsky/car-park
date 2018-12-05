package cz.pa165.carpark.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="The requested object was not found")
public class MissingObjectException extends RuntimeException {
}
