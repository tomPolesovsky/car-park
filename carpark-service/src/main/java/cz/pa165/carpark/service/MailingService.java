package cz.pa165.carpark.service;

import cz.pa165.carpark.entity.Reservation;

/**
 * The mailing service's interface.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
public interface MailingService {

    /**
     * Sends confirmation to the employee that the car was successfully reserved
     */
    void SendConfirmation(Reservation reservation);

    /**
     * Sends declination to the employee - the reservation of the car was not approved
     */
    void SendDeclination(Reservation reservation);

    /**
     * Sends request for approval to the approver
     */
    void SendRequestForApproval(Reservation reservation);
}
