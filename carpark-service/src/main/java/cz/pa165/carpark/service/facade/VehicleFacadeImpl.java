package cz.pa165.carpark.service.facade;

import cz.pa165.carpark.api.dto.VehicleDTO;
import cz.pa165.carpark.api.facade.VehicleFacade;
import cz.pa165.carpark.persistence.entity.Vehicle;
import cz.pa165.carpark.service.service.VehicleService;
import cz.pa165.carpark.service.util.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * The reservation facade's implementation.
 * Possibility to create, update, delete, find, find by registration number, find all (vehicle/s) via this facade.
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
@Transactional
@Service
public class VehicleFacadeImpl implements VehicleFacade {

    private ObjectMapper objectMapper;

    private VehicleService vehicleService;

    @Inject
    public VehicleFacadeImpl(ObjectMapper objectMapper, VehicleService vehicleService) {
        this.objectMapper = objectMapper;
        this.vehicleService = vehicleService;
    }

    /**
     * Find the vehicle with the specified id
     *
     * @param id unambiguous identification of entity
     * @return vehicle dto
     */
    @Override
    public VehicleDTO find(Long id) {
        Vehicle vehicle = vehicleService.find(id);
        return (vehicle == null) ? null : objectMapper.mapTo(vehicle, VehicleDTO.class);
    }

    /**
     * Find vehicle with the specified registration number
     *
     * @param registrationNumber
     * @return vehicle dto
     */
    @Override
    public VehicleDTO findByRegistrationNumber(String registrationNumber) {
        Vehicle vehicle = vehicleService.findByRegistrationNumber(registrationNumber);
        return (vehicle == null) ? null : objectMapper.mapTo(vehicle, VehicleDTO.class);
    }

    /**
     * Find all vehicles
     *
     * @return list of vehicles' dtos
     */
    @Override
    public List<VehicleDTO> findAll() {
        List<Vehicle> list = vehicleService.findAll();
        return objectMapper.mapTo(list, VehicleDTO.class);
    }

    /**
     * Create new vehicle
     *
     * @param vehicle dto
     * @return vehicle dto
     */
    @Override
    public VehicleDTO create(VehicleDTO vehicle) {
        Vehicle vehicleEntity = objectMapper.mapTo(vehicle, Vehicle.class);
        vehicleService.save(vehicleEntity);
        return objectMapper.mapTo(vehicleEntity, VehicleDTO.class);
    }

    /**
     * Update the specified vehicle
     *
     * @param vehicle dto
     * @return vehicle dto
     */
    @Override
    public VehicleDTO update(VehicleDTO vehicle) {
        Vehicle vehicleEntity = objectMapper.mapTo(vehicle, Vehicle.class);
        vehicleService.update(vehicleEntity);
        return objectMapper.mapTo(vehicleEntity, VehicleDTO.class);
    }

    /**
     * Delete the vehicle with the specified id
     *
     * @param id unambiguous identification of entity
     */
    @Override
    public void delete(Long id) {
        vehicleService.delete(id);
    }

}
