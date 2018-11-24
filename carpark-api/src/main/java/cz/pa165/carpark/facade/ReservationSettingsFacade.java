package cz.pa165.carpark.facade;

import cz.pa165.carpark.dto.EmployeeDTO;
import cz.pa165.carpark.dto.ReservationSettingsDTO;

import java.util.List;

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
     * @return list of reservation settingss
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
     */
    ReservationSettingsDTO create(ReservationSettingsDTO reservationSettings);

    /**
     * Update the specified reservation settings
     *
     * @param reservationSettings dto
     */
    ReservationSettingsDTO update(ReservationSettingsDTO reservationSettings);

    /**
     * Delete the reservation with the specified id
     *
     * @param id unambiguous identification of entity
     */
    void delete(Long id);

}
