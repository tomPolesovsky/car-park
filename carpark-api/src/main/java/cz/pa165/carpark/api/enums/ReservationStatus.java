package cz.pa165.carpark.api.enums;

/**
 * The status of the reservation
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
public enum ReservationStatus {

    /**
     * Reservation is new
     */
    NEW,
    /**
     * Reservation was approved
     */
    APPROVED,
    /**
     * Reservation was returned
     */
    RETURNED,
    /**
     * Reservation was canceled
     */
    CANCELED

}

