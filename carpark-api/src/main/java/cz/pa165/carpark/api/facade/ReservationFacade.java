package cz.pa165.carpark.api.facade;

import cz.pa165.carpark.api.dto.EmployeeDTO;
import cz.pa165.carpark.api.dto.ReservationDTO;
import cz.pa165.carpark.api.dto.ReservationParamsDTO;
import cz.pa165.carpark.api.dto.VehicleDTO;

import java.util.List;

/**
 * The reservation facade's interface.
 * Possibility to process reservation request, approve or decline reservation request, update, delete, filter,
 * find, find by employee, find by vehicle, find all via this facade.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
public interface ReservationFacade {

    /**
     * Find the reservation with the specified id
     *
     * @param id unambiguous identification of entity
     * @return reservation dto
     */
    ReservationDTO find(Long id);

    /**
     * Find all the reservations for the specified employee
     *
     * @param employee dto
     * @return list of reservations
     */
    List<ReservationDTO> findByEmployee(EmployeeDTO employee);

    /**
     * Find all the reservations for the specified vehicle
     *
     * @param vehicle dto
     * @return list of reservations
     */
    List<ReservationDTO> findByVehicle(VehicleDTO vehicle);

    /**
     * Find all reservations
     *
     * @return list of reservation' dtos
     */
    List<ReservationDTO> findAll();

    /**
     * Processes the reservation request
     *
     * @param reservation dto
     * @return reservation dto
     */
    ReservationDTO processRequest(ReservationDTO reservation);

    /**
     * Accepts or declines the reservation request
     *
     * @param reservation dto
     * @param toBeAccepted
     */
    void acceptOrDecline(ReservationDTO reservation, boolean toBeAccepted);

    /**
     * Update the specified reservation
     *
     * @param reservation dto
     * @return reservation dto
     */
    ReservationDTO update(ReservationDTO reservation);

    /**
     * Delete the reservation with the specified id
     *
     * @param id unambiguous identification of entity
     */
    void delete(Long id);

    /**
     * Filter all the reservations according to the input params
     *
     * @param reservationParams
     * @return list of reservations
     */
    List<ReservationDTO> filter(ReservationParamsDTO reservationParams);

}
