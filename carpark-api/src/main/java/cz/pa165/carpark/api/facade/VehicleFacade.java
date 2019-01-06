package cz.pa165.carpark.api.facade;

import cz.pa165.carpark.api.dto.VehicleDTO;

import java.util.List;

/**
 * The vehicle facade's interface.
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
public interface VehicleFacade {
    /**
     * Find the vehicle with the specified id
     *
     * @param id unambiguous identification of entity
     * @return vehicle dto
     */
    VehicleDTO find(Long id);

    /**
     * Find the vehicle for the specified registration number
     *
     * @param registrationNumber
     * @return vehicle dto
     */
    VehicleDTO findByRegistrationNumber(String registrationNumber);

    /**
     * Find all vehicles
     *
     * @return list of vehicle dtos
     */
    List<VehicleDTO> findAll();

    /**
     * Create new vehicle
     *
     * @param vehicle dto
     * @return vehicle dto
     */
    VehicleDTO create(VehicleDTO vehicle);

    /**
     * Update the specified vehicle
     *
     * @param vehicle dto
     * @return vehicle dto
     */
    VehicleDTO update(VehicleDTO vehicle);

    /**
     * Delete the vehicle with the specified id
     *
     * @param id unambiguous identification of entity
     */
    void delete(Long id);

}
