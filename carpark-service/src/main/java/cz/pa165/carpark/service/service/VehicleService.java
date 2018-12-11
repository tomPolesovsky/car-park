package cz.pa165.carpark.service.service;

import cz.pa165.carpark.persistence.entity.Vehicle;

import java.util.List;

/**
 * The vehicle service's interface.
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
public interface VehicleService {

    /**
     * Find the vehicle settings with the specified id
     *
     * @param id unambiguous identification of entity
     * @return vehicle settings
     */
    Vehicle find(Long id);

    /**
     * Find all the vehicle for the specified registration number
     *
     * @param registrationNumber
     * @return list of vehicle
     */
    Vehicle findByRegistrationNumber(String registrationNumber);

    /**
     * Find all vehicles
     *
     * @return list of all vehicles
     */
    List<Vehicle> findAll();

    /**
     * Save the specified vehicle
     *
     * @param vehicle
     */
    void save(Vehicle vehicle);

    /**
     * Update the specified vehicle
     *
     * @param vehicle
     */
    void update(Vehicle vehicle);

    /**
     * Delete the vehicle with the specified id
     *
     * @param id unambiguous identification of entity
     */
    void delete(Long id);

}
