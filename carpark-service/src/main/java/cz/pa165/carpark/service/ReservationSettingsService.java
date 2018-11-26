package cz.pa165.carpark.service;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.ReservationSettings;
import java.util.List;

/**
 * The reservation settings service's interface.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
public interface ReservationSettingsService {

    /**
     * Find all the reservation settings for the specified employee
     *
     * @param employee
     * @return list of reservations settings
     */
    ReservationSettings findByEmployee(Employee employee);

    /**
     * Find the reservation settings with the specified id
     *
     * @param id unambiguous identification of entity
     * @return reservation settings
     */
    ReservationSettings find(Long id);

    /**
     * Find all reservation settings
     *
     * @return list of all reservation settings
     */
    List<ReservationSettings> findAll();

    /**
     * Save the specified reservation settings
     *
     * @param reservationSettings
     */
    void save(ReservationSettings reservationSettings);

    /**
     * Update the specified reservation settings
     *
     * @param reservationSettings
     */
    void update(ReservationSettings reservationSettings);

    /**
     * Delete the reservation settings with the specified id
     *
     * @param id unambiguous identification of entity
     */
    void delete(Long id);

}
