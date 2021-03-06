package cz.pa165.carpark.persistence.dao;

import cz.pa165.carpark.persistence.entity.Employee;
import cz.pa165.carpark.persistence.entity.ReservationSettings;
import org.springframework.stereotype.Repository;

/**
 * Implementation for ReservationSettings' DAO.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@Repository
public class ReservationSettingsDaoImpl extends DaoImpl<ReservationSettings> implements ReservationSettingsDao {

    /**
     * Initializes a new instance of ReservationSettingsDaoImpl class.
     */
    public ReservationSettingsDaoImpl() {
        super(ReservationSettings.class);
    }

    /**
     * Find settings for specified employee.
     *
     * @param employee
     * @return settings
     */
    @Override
    public ReservationSettings findByEmployee(Employee employee) {
        return em.createQuery("select s from ReservationSettings s where s.employee = :employee", ReservationSettings.class)
                .setParameter("employee", employee)
                .getSingleResult();
    }

}
