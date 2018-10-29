package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.ReservationSettings;

/**
 * Interface for ReservationSettings' DAO
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
public interface ReservationSettingsDao extends Dao<ReservationSettings>  {

    /**
     * Find settings for specified employee.
     *
     * @param employee
     * @return settings
     */
    ReservationSettings findByEmployee(Employee employee);

}
