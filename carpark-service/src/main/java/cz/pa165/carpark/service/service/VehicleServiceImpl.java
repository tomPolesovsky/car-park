package cz.pa165.carpark.service.service;

import cz.pa165.carpark.persistence.dao.VehicleDao;
import cz.pa165.carpark.persistence.entity.Vehicle;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * The vehicle service's implementation.
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 * @author Jana Applova, 422352@mail.muni.cz
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleDao vehicleDao;

    @Inject
    public VehicleServiceImpl(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    /**
     * Find the vehicle settings with the specified id
     *
     * @param id unambiguous identification of entity
     * @return vehicle settings
     */
    @Override
    public Vehicle find(Long id) {
        return vehicleDao.find(id);
    }

    /**
     * Find all the vehicle for the specified registration number
     *
     * @param registrationNumber
     * @return list of vehicle
     */
    @Override
    public Vehicle findByRegistrationNumber(String registrationNumber) {
        return vehicleDao.findByRegistrationNumber(registrationNumber);
    }

    /**
     * Find all vehicles
     *
     * @return list of all vehicles
     */
    @Override
    public List<Vehicle> findAll() {
        return vehicleDao.findAll();
    }

    /**
     * Save the specified vehicle
     *
     * @param vehicle
     */
    @Override
    public void save(Vehicle vehicle) {
        vehicleDao.save(vehicle);
    }

    /**
     * Update the specified vehicle
     *
     * @param vehicle
     */
    @Override
    public void update(Vehicle vehicle) {
        vehicleDao.update(vehicle);
    }

    /**
     * Delete the vehicle with the specified id
     *
     * @param id unambiguous identification of entity
     */
    @Override
    public void delete(Long id) {
        vehicleDao.delete(id);
    }

}
