package cz.pa165.carpark.controller;

import cz.pa165.carpark.config.ApiConfiguration;
import cz.pa165.carpark.dto.EmployeeDTO;
import cz.pa165.carpark.dto.ReservationDTO;
import cz.pa165.carpark.dto.ReservationParamsDTO;
import cz.pa165.carpark.dto.VehicleDTO;
import cz.pa165.carpark.exception.FailedOperationException;
import cz.pa165.carpark.exception.MissingObjectException;
import cz.pa165.carpark.facade.EmployeeFacade;
import cz.pa165.carpark.facade.ReservationFacade;
import cz.pa165.carpark.facade.VehicleFacade;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

import static cz.pa165.carpark.util.ApiUtils.notNull;

/**
 * Reservation REST API
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@RestController
@RequestMapping(ApiConfiguration.API_RESERVATIONS)
public class ReservationController {

    private ReservationFacade reservationFacade;
    private VehicleFacade vehicleFacade;
    private EmployeeFacade employeeFacade;

    @Inject
    public ReservationController(ReservationFacade reservationFacade,
                                 VehicleFacade vehicleFacade,
                                 EmployeeFacade employeeFacade) {
        this.reservationFacade = reservationFacade;
        this.vehicleFacade = vehicleFacade;
        this.employeeFacade = employeeFacade;
    }

    /**
     * Find reservation by its id.
     *
     * @param id
     * @return vehicle dto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ReservationDTO find(@PathVariable("id") Long id) {
        ReservationDTO reservation = reservationFacade.find(id);
        notNull(reservation, MissingObjectException::new);

        return reservation;
    }

    /**
     * Find reservations for the specified employee.
     *
     * @param employee dto
     * @return list of reservation dto
     */
    @RequestMapping(value = "/find-by-employee", method = RequestMethod.GET)
    public List<ReservationDTO> findByEmployee(@RequestBody EmployeeDTO employee) {
        EmployeeDTO result = employeeFacade.find(employee.getId());
        notNull(result, MissingObjectException::new);

        return reservationFacade.findByEmployee(employee);
    }

    /**
     * Find reservations for the specified vehicle.
     *
     * @param vehicle dto
     * @return list of reservation dto
     */
    @RequestMapping(value = "/find-by-vehicle", method = RequestMethod.GET)
    public List<ReservationDTO> findByVehicle(@RequestBody VehicleDTO vehicle) {
        VehicleDTO result = vehicleFacade.find(vehicle.getId());
        notNull(result, MissingObjectException::new);

        return reservationFacade.findByVehicle(vehicle);
    }

    /**
     * Find all reservations.
     *
     * @return list of reservation dto
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<ReservationDTO> findAll() {
        return reservationFacade.findAll();
    }

    /**
     * Process reservation request.
     *
     * @param reservation dto
     * @return reservation dto
     */
    @RequestMapping(value = "/process-request", method = RequestMethod.POST)
    public ReservationDTO processRequest(@RequestBody ReservationDTO reservation) {
        ReservationDTO result = reservationFacade.processRequest(reservation);
        notNull(result, FailedOperationException::new);

        return result;
    }

    /**
     * Accept or decline reservation request.
     *
     * @param reservation dto
     * @param toBeAccepted
     */
    @RequestMapping(value = "/accept-or-decline", method = RequestMethod.PUT)
    public void acceptOrDecline(@RequestBody ReservationDTO reservation,
                                @RequestParam("toBeAccepted") boolean toBeAccepted) {
        ReservationDTO result = reservationFacade.find(reservation.getId());
        notNull(result, MissingObjectException::new);

        reservationFacade.acceptOrDecline(reservation, toBeAccepted);
    }

    /**
     * Update reservation.
     *
     * @param reservation dto
     * @return reservation dto
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ReservationDTO update(@RequestBody ReservationDTO reservation) {
        ReservationDTO result = reservationFacade.update(reservation);
        notNull(result, FailedOperationException::new);

        return result;
    }

    /**
     * Delete reservation with the specified id.
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        ReservationDTO reservation = reservationFacade.find(id);
        notNull(reservation, MissingObjectException::new);

        reservationFacade.delete(id);
    }

    /**
     * Filter reservations.
     *
     * @param reservationParams dto
     * @return list of reservation dto
     */
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public List<ReservationDTO> filter(@RequestBody ReservationParamsDTO reservationParams) {
        return reservationFacade.filter(reservationParams);
    }

}
