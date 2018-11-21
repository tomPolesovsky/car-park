package cz.pa165.carpark.service;

import cz.pa165.carpark.dao.ReservationSettingsDao;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.ReservationSettings;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * The reservation settings service's implementation.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@Service
public class ReservationSettingsServiceImpl implements ReservationSettingsService {

    @Inject
    private ReservationSettingsDao reservationSettingsDao;

    @Override
    public ReservationSettings findByEmployee(Employee employee) {
        return reservationSettingsDao.findByEmployee(employee);
    }

}
