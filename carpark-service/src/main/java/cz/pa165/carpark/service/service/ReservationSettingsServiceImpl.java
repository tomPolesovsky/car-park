package cz.pa165.carpark.service.service;

import cz.pa165.carpark.persistence.dao.ReservationSettingsDao;
import cz.pa165.carpark.persistence.entity.Employee;
import cz.pa165.carpark.persistence.entity.ReservationSettings;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * The reservation settings service's implementation.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@Service
public class ReservationSettingsServiceImpl implements ReservationSettingsService {

    private ReservationSettingsDao reservationSettingsDao;

    @Inject
    public ReservationSettingsServiceImpl(ReservationSettingsDao reservationSettingsDao) {
        this.reservationSettingsDao = reservationSettingsDao;
    }

    /**
     * Find all the reservation settings for the specified employee
     *
     * @param employee
     * @return list of reservations settings
     */
    @Override
    public ReservationSettings findByEmployee(Employee employee) {
        return reservationSettingsDao.findByEmployee(employee);
    }

    /**
     * Find the reservation settings with the specified id
     *
     * @param id unambiguous identification of entity
     * @return reservation settings
     */
    @Override
    public ReservationSettings find(Long id) {
        return reservationSettingsDao.find(id);
    }

    /**
     * Find all reservation settings
     *
     * @return list of all reservation settings
     */
    @Override
    public List<ReservationSettings> findAll() {
        return reservationSettingsDao.findAll();
    }

    /**
     * Save the specified reservation settings
     *
     * @param reservationSettings
     */
    @Override
    public void save(ReservationSettings reservationSettings) {
        reservationSettingsDao.save(reservationSettings);
    }

    /**
     * Update the specified reservation settings
     *
     * @param reservationSettings
     */
    @Override
    public void update(ReservationSettings reservationSettings) {
        reservationSettingsDao.update(reservationSettings);
    }

    /**
     * Delete the reservation settings with the specified id
     *
     * @param id unambiguous identification of entity
     */
    @Override
    public void delete(Long id) {
        reservationSettingsDao.delete(id);
    }

}
