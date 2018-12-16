package cz.pa165.carpark.rest.controller;

import cz.pa165.carpark.api.dto.EmployeeDTO;
import cz.pa165.carpark.api.dto.ReservationSettingsDTO;
import cz.pa165.carpark.api.facade.ReservationSettingsFacade;
import cz.pa165.carpark.rest.config.ApiConfiguration;
import cz.pa165.carpark.rest.exception.FailedOperationException;
import cz.pa165.carpark.rest.exception.MissingObjectException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

import static cz.pa165.carpark.rest.util.ApiUtils.notNull;

/**
 * Reservation Settings REST API
 *
 * @author Ondrej Svoren, 487558@mail.muni.cz
 */
@RestController
@RequestMapping(ApiConfiguration.API_RESERVATION_SETTINGS)
public class ReservationSettingsController {

    private ReservationSettingsFacade reservationSettingsFacade;

    @Inject
    public ReservationSettingsController(ReservationSettingsFacade reservationSettingsFacade) {
        this.reservationSettingsFacade = reservationSettingsFacade;
    }

    /**
     * Find reservation settings by its id.
     *
     * @param id
     * @return reservation settings dto
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ReservationSettingsDTO find(@PathVariable("id") Long id) {
        ReservationSettingsDTO result = reservationSettingsFacade.find(id);
        notNull(result, MissingObjectException::new);

        return result;
    }

    /**
     * Find reservation settings for the specified employee.
     *
     * @param employee dto
     * @return reservation settings dto
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/find-by-employee", method = RequestMethod.GET)
    public ReservationSettingsDTO findByEmployee(@Valid @RequestBody EmployeeDTO employee) {
        ReservationSettingsDTO result = reservationSettingsFacade.findByEmployee(employee);
        notNull(result, MissingObjectException::new);

        return result;
    }

    /**
     * Find all reservation settings.
     *
     * @return list of reservation settings dto
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET)
    public List<ReservationSettingsDTO> findAll() {
        return reservationSettingsFacade.findAll();
    }

    /**
     * Create reservation settings.
     *
     * @param reservationSettings dto
     * @return reservationSettings dto
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST)
    public ReservationSettingsDTO create(@Valid @RequestBody ReservationSettingsDTO reservationSettings) {
        ReservationSettingsDTO result = reservationSettingsFacade.create(reservationSettings);
        notNull(result, FailedOperationException::new);

        return result;
    }

    /**
     * Update reservation settings.
     *
     * @param reservationSettings dto
     * @return reservationSettings dto
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.PUT)
    public ReservationSettingsDTO update(@Valid @RequestBody ReservationSettingsDTO reservationSettings) {
        ReservationSettingsDTO result = reservationSettingsFacade.update(reservationSettings);
        notNull(result, FailedOperationException::new);

        return result;
    }

    /**
     * Delete reservation settings with the specified id.
     *
     * @param id
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        ReservationSettingsDTO result = reservationSettingsFacade.find(id);
        notNull(result, MissingObjectException::new);

        reservationSettingsFacade.delete(id);
    }

}