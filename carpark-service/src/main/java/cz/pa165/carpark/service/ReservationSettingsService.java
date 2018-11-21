package cz.pa165.carpark.service;

import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.ReservationSettings;
import org.springframework.stereotype.Service;

/**
 * The reservation settings service's interface.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@Service
public interface ReservationSettingsService {

    public ReservationSettings findByEmployee(Employee employee);

}
