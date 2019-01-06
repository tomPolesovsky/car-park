package cz.pa165.carpark.api.facade;

import cz.pa165.carpark.api.dto.EmployeeDTO;
import cz.pa165.carpark.api.dto.ReservationSettingsDTO;

import java.util.List;

/**
 * The reservation settings facade's implementation.
 * Possibility to create, update, delete, find, find by employee, find all (reservation settings) via this facade.
 *
 * @author Ondrej Svoren, 487558@mail.muni.cz
 */
public interface ReservationSettingsFacade {
    /**
     * Find the reservation settings with the specified id
     *
     * @param id unambiguous identification of entity
     * @return reservation settings dto
     */
    ReservationSettingsDTO find(Long id);

    /**
     * Find all the reservation settingss for the specified employee
     *
     * @param employee
     * @return list of reservation settings
     */
    ReservationSettingsDTO findByEmployee(EmployeeDTO employee);

    /**
     * Find all reservation settingss
     *
     * @return list of reservation settings' dtos
     */
    List<ReservationSettingsDTO> findAll();

    /**
     * Create new reservation settings
     *
     * @param reservationSettings dto
     * @return reservation settings dto
     */
    ReservationSettingsDTO create(ReservationSettingsDTO reservationSettings);

    /**
     * Update the specified reservation settings
     *
     * @param reservationSettings dto
     * @return reservation settings dto
     */
    ReservationSettingsDTO update(ReservationSettingsDTO reservationSettings);

    /**
     * Delete the reservation with the specified id
     *
     * @param id unambiguous identification of entity
     */
    void delete(Long id);

}
