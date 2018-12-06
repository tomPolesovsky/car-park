package cz.pa165.carpark.controller;

import cz.pa165.carpark.config.ApiConfiguration;
import cz.pa165.carpark.dto.EmployeeDTO;
import cz.pa165.carpark.dto.ReservationDTO;
import cz.pa165.carpark.dto.ReservationParamsDTO;
import cz.pa165.carpark.dto.VehicleDTO;
import cz.pa165.carpark.exception.FailedOperationException;
import cz.pa165.carpark.exception.MissingObjectException;
import cz.pa165.carpark.facade.ReservationFacade;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

import static cz.pa165.carpark.util.ApiUtils.notNull;

@RestController
@RequestMapping(ApiConfiguration.API_RESERVATIONS)
public class ReservationController {

    private ReservationFacade reservationFacade;

    @Inject
    public ReservationController(ReservationFacade reservationFacade) {
        this.reservationFacade = reservationFacade;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ReservationDTO find(@PathVariable("id") Long id) {
        ReservationDTO reservation = reservationFacade.find(id);
        notNull(reservation, MissingObjectException::new);

        return reservation;
    }

    @RequestMapping(value = "/findByEmployee", method = RequestMethod.GET)
    public List<ReservationDTO> findByEmployee(@RequestBody EmployeeDTO employee) {
        return reservationFacade.findByEmployee(employee);
    }

    @RequestMapping(value = "/findByVehicle", method = RequestMethod.GET)
    public List<ReservationDTO> findByVehicle(@RequestBody VehicleDTO vehicle) {
        return reservationFacade.findByVehicle(vehicle);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ReservationDTO> findAll() {
        return reservationFacade.findAll();
    }

    @RequestMapping(value = "/processRequest", method = RequestMethod.POST)
    public ReservationDTO processRequest(@RequestBody ReservationDTO reservation) {
        ReservationDTO result = reservationFacade.processRequest(reservation);
        notNull(result, FailedOperationException::new);

        return result;
    }

    @RequestMapping(value = "/acceptOrDecline", method = RequestMethod.PUT)
    public void acceptOrDecline(@RequestBody ReservationDTO reservation,
                                @RequestParam("toBeAccepted") boolean toBeAccepted) {
        reservationFacade.acceptOrDecline(reservation, toBeAccepted);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ReservationDTO update(@RequestBody ReservationDTO reservation) {
        ReservationDTO result = reservationFacade.update(reservation);
        notNull(result, FailedOperationException::new);

        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        ReservationDTO reservation = reservationFacade.find(id);
        notNull(reservation, MissingObjectException::new);

        reservationFacade.delete(id);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public List<ReservationDTO> filter(@RequestBody ReservationParamsDTO reservationParams) {
        return reservationFacade.filter(reservationParams);
    }

}
