package cz.pa165.carpark.facade;

import cz.pa165.carpark.dto.VehicleDTO;

import java.util.List;

/**
 * The vehicle facade's interface.
 *
 * @author Tomáš Polešovský, polesovsky@gmail.com
 */
public interface VehicleFacade {

    VehicleDTO find(Long id);

    VehicleDTO findByRegistrationNumber(String registrationNumber);

    List<VehicleDTO> findAll();

    VehicleDTO create(VehicleDTO vehicle);

    VehicleDTO update(VehicleDTO vehicle);

    void delete(Long id);

}
