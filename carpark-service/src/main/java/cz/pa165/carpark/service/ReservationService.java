package cz.pa165.carpark.service;

import cz.pa165.carpark.dto.ReservationDTO;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import cz.pa165.carpark.entity.ReservationSettings;
import cz.pa165.carpark.entity.Vehicle;

import java.util.List;

/**
 * The reservation service's interface.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
public interface ReservationService {

    /**
     * Find the reservation with the specified id
     *
     * @param id unambiguous identification of entity
     * @return reservation
     */
    Reservation find(Long id);

    /**
     * Find all the reservations for the specified employee
     *
     * @param employee
     * @return list of reservations
     */
    List<Reservation> findByEmployee(Employee employee);

    /**
     * Find all the reservations for the specified vehicle
     *
     * @param vehicle
     * @return list of reservations
     */
    List<Reservation> findByVehicle(Vehicle vehicle);

    /**
     * Find all reservations
     *
     * @return list of all reservations
     */
    List<Reservation> findAll();

    /**
     * Processes reservation request
     *
     * @param reservation
     * @param reservationSettings
     * @return true if the request was successfully processed else false
     */
    void processRequest(Reservation reservation, ReservationSettings reservationSettings);

    /**
     * Accepts or declines the reservation request
     *
     * @param reservation
     * @param toBeAccepted
     */
    void acceptOrDecline(Reservation reservation, boolean toBeAccepted);

    /**
     * Update the specified reservation
     *
     * @param reservation
     */
    void update(Reservation reservation);

    /**
     * Delete the reservation with the specified id
     *
     * @param id unambiguous identification of entity
     */
    void delete(Long id);

    /**
     * Filter all the reservations according to the input params
     *
     * @param reservationFilterParams
     * @return list of reservations
     */
    List<Reservation> filter(ReservationFilterParams reservationFilterParams);
}
