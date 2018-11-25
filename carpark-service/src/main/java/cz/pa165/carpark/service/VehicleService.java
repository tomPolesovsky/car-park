package cz.pa165.carpark.service;

import cz.pa165.carpark.entity.Vehicle;

import java.util.List;

/**
 * The vehicle service's interface.
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
public interface VehicleService {

    Vehicle find(Long id);

    Vehicle findByRegistrationNumber(String registrationNumber);

    List<Vehicle> findAll();

    void save(Vehicle vehicle);

    void update(Vehicle vehicle);

    void delete(Long id);

}
