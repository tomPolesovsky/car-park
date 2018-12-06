package cz.pa165.carpark.controller;

import cz.pa165.carpark.config.ApiConfiguration;
import cz.pa165.carpark.dto.VehicleDTO;
import cz.pa165.carpark.exception.FailedOperationException;
import cz.pa165.carpark.exception.MissingObjectException;
import cz.pa165.carpark.facade.VehicleFacade;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

import static cz.pa165.carpark.util.ApiUtils.notNull;

/**
 * Vehicle REST API
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@RestController
@RequestMapping(ApiConfiguration.API_VEHICLES)
public class VehicleController {

    private VehicleFacade vehicleFacade;

    @Inject
    public VehicleController(VehicleFacade vehicleFacade) {
        this.vehicleFacade = vehicleFacade;
    }

    /**
     * Find vehicle by its id.
     *
     * @param id
     * @return vehicle dto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public VehicleDTO find(@PathVariable("id") Long id) {
        VehicleDTO result = vehicleFacade.find(id);
        notNull(result, MissingObjectException::new);

        return result;
    }

    /**
     * Find vehicle by its registration number.
     *
     * @param registration-number
     * @return vehicle dto
     */
    @RequestMapping(value = "/{registration-number}", method = RequestMethod.GET)
    public VehicleDTO findByRegistrationNumber(@PathVariable("registration-number") String registrationNumber) {
        VehicleDTO result = vehicleFacade.findByRegistrationNumber(registrationNumber);
        notNull(result, MissingObjectException::new);

        return result;
    }

    /**
     * Find all vehicles.
     *
     * @return list of vehicle dto
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<VehicleDTO> findAll() {
        return vehicleFacade.findAll();
    }

    /**
     * Create vehicle.
     *
     * @param vehicle dto
     * @return vehicle dto
     */
    @RequestMapping(method = RequestMethod.POST)
    public VehicleDTO create(@RequestBody VehicleDTO vehicle) {
        VehicleDTO result = vehicleFacade.create(vehicle);
        notNull(result, FailedOperationException::new);

        return result;
    }

    /**
     * Update vehicle.
     *
     * @param vehicle dto
     * @return vehicle dto
     */
    @RequestMapping(method = RequestMethod.PUT)
    public VehicleDTO update(@RequestBody VehicleDTO vehicle) {
        VehicleDTO result = vehicleFacade.update(vehicle);
        notNull(result, FailedOperationException::new);

        return result;
    }

    /**
     * Delete vehicle with the specified id.
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        VehicleDTO result = vehicleFacade.find(id);
        notNull(result, MissingObjectException::new);

        vehicleFacade.delete(id);
    }

}
