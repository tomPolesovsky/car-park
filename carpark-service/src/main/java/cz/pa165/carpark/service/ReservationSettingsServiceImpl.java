package cz.pa165.carpark.service;
import cz.pa165.carpark.dao.ReservationSettingsDao;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.ReservationSettings;
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

    @Inject
    private ReservationSettingsDao reservationSettingsDao;

    @Inject
    public ReservationSettingsServiceImpl(ReservationSettingsDao reservationSettingsDao) {
        this.reservationSettingsDao = reservationSettingsDao;
    }

    @Override
    public ReservationSettings findByEmployee(Employee employee) {
        return reservationSettingsDao.findByEmployee(employee);
    }

    @Override
    public ReservationSettings find(Long id) {
        return reservationSettingsDao.find(id);
    }

    @Override
    public List<ReservationSettings> findAll() {
        return reservationSettingsDao.findAll();
    }

    @Override
    public void save(ReservationSettings reservationSettings) {
        reservationSettingsDao.save(reservationSettings);
    }

    @Override
    public void update(ReservationSettings reservationSettings) {
        reservationSettingsDao.update(reservationSettings);
    }

    @Override
    public void delete(Long id) {
        reservationSettingsDao.delete(id);
    }

}
