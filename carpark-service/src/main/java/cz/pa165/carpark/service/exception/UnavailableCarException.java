package cz.pa165.carpark.service.exception;

/**
 * The exception that will be thrown if no specified car will be available at the time of the reservation
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
public class UnavailableCarException extends RuntimeException {

    public UnavailableCarException() {
        super();
    }

    public UnavailableCarException(String message) {
        super(message);
    }

    public UnavailableCarException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnavailableCarException(Throwable cause) {
        super(cause);
    }

    public UnavailableCarException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
