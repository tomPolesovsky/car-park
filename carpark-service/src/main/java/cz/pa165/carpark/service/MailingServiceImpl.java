package cz.pa165.carpark.service;

import cz.pa165.carpark.entity.Reservation;
import org.springframework.stereotype.Service;

/**
 * The mailing service's implementation.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@Service
public class MailingServiceImpl implements MailingService {

    /**
     * Sends confirmation to the employee that the car was successfully reserved
     * The method does not actually send an email - there is no server so we fake it by printing it out
     */
    @Override
    public void sendConfirmation(Reservation reservation) {
        System.out.println("Your reservation of a car with a registration number " +
                reservation.getVehicle().getRegistrationNumber() + " from date " +
                reservation.getFrom() + " to date " + reservation.getTo() + " was successful.");
    }

    /**
     * Sends declination to the employee - the reservation of the car was not approved
     * The method does not actually send an email - there is no server so we fake it by printing it out
     */
    @Override
    public void sendDeclination(Reservation reservation) {
        System.out.println("Your reservation of a car with a registration number " +
                reservation.getVehicle().getRegistrationNumber() + " from date " +
                reservation.getFrom() + " to date " + reservation.getTo() + " was declined.");
    }

    /**
     * Sends request for approval to the approver
     * The method does not actually send an email - there is no server so we fake it by printing it out
     */
    @Override
    public void sendRequestForApproval(Reservation reservation) {
        System.out.println("You were requested to approve a reservation for " +
                reservation.getEmployee().getFirstName() + " " + reservation.getEmployee().getLastName() + ".");
    }

}
