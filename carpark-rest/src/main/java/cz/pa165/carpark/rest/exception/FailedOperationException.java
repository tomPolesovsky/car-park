package cz.pa165.carpark.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="The requested operation failed")
public class FailedOperationException extends RuntimeException {

}
