package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.ReservationSettings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation for ReservationSettings' DAO.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@Transactional
@Repository
public class ReservationSettingsDaoImpl extends DaoImpl<ReservationSettings> implements ReservationSettingsDao  {

    /**
     * Initializes a new instance of ReservationSettingsDaoImpl class.
     */
    public ReservationSettingsDaoImpl() {
        super(ReservationSettings.class);
    }

    /**
     * Find settings for specified employee.
     * @param employee
     * @return settings
     */
    @Override
    public ReservationSettings findByEmployee(Employee employee) {
        return null;
    }
}
