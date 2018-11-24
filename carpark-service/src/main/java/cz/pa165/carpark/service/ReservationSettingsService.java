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

    ReservationSettings findByEmployee(Employee employee);

    ReservationSettings find(Long id);

    List<ReservationSettings> findAll();

    void save(ReservationSettings reservationSettings);

    void update(ReservationSettings reservationSettings);

    void delete(Long id);

}
