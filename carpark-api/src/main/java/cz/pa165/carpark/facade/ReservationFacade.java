package cz.pa165.carpark.facade;

import cz.pa165.carpark.dto.EmployeeDTO;
import cz.pa165.carpark.dto.ReservationDTO;
import cz.pa165.carpark.dto.ReservationParamsDTO;
import cz.pa165.carpark.dto.VehicleDTO;

import java.util.List;

/**
 * The reservation facade's interface.
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
     * @param employee
     * @return list of reservations
     */
    List<ReservationDTO> findByEmployee(EmployeeDTO employee);

    /**
     * Find all the reservations for the specified vehicle
     *
     * @param vehicle
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
     * Create new reservation
     *
     * @param reservation dto
     */
    ReservationDTO create(ReservationDTO reservation);

    /**
     * Update the specified reservation
     *
     * @param reservation dto
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
