package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Vehicle;

/**
 * Extended {@link Dao} for Vehicle
 *
 * @author Ondrej Svoreň
 */
public interface VehicleDao extends Dao<Vehicle> {

    /**
     * Find specific vehicle by its registration number
     *
     * @param registrationNumber registration number of vehicle
     * @return entity object or null, if entity was not found
     */
    Vehicle findByRegistrationNumber(String registrationNumber);

}
