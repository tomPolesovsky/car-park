package cz.pa165.carpark.dao;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.ReservationSettings;
import cz.pa165.carpark.util.AbstractJUnitTest;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;

/**
 * Tests for ReservationSettings' DAO implementation.
 *
 * @author Ondrej Svoreň
 */
@Transactional
public class ReservationSettingsDaoTest extends AbstractJUnitTest {

    @Inject
    private ReservationSettingsDao reservationSettingsDao;

    @Test
    public void findByEmployee() {
        ReservationSettings reservationSettings = new ReservationSettings();
        Employee employee = new Employee();
        employee.setUsername("Username1");
        em.persist(employee);

        reservationSettings.setEmployee(employee);
        em.persist(reservationSettings);

        ReservationSettings reservationSettingsResult = reservationSettingsDao.findByEmployee(
                reservationSettings.getEmployee()
        );

        assertEquals(employee, reservationSettingsResult.getEmployee());
    }

    @Test(expected = DataAccessException.class)
    public void findByEmployeeNotExists() {
        Employee employee2 = new Employee();
        employee2.setUsername("Username2");
        em.persist(employee2);

        reservationSettingsDao.findByEmployee(employee2);
    }

    @Test
    public void findAll() {
        ReservationSettings firstReservationSettings = new ReservationSettings();
        Employee firstEmployee = new Employee();
        firstEmployee.setUsername("Username1");
        em.persist(firstEmployee);
        firstReservationSettings.setEmployee(firstEmployee);
        em.persist(firstReservationSettings);

        ReservationSettings secondReservationSettings = new ReservationSettings();
        Employee secondEmployee = new Employee();
        secondEmployee.setUsername("Username2");
        em.persist(secondEmployee);
        secondReservationSettings.setEmployee(secondEmployee);
        em.persist(secondReservationSettings);

        List<ReservationSettings> reservationSettingsResult = reservationSettingsDao.findAll();
        assertThat(reservationSettingsResult, hasSize(2));
        assertThat(reservationSettingsResult, contains(
                hasProperty("employee",
                        equalTo(firstEmployee)),
                hasProperty("employee",
                        equalTo(secondEmployee))
        ));
    }

    @Test
    public void save() {
        ReservationSettings reservationSettings = new ReservationSettings();
        Employee employee = new Employee();
        employee.setUsername("Username1");
        em.persist(employee);

        reservationSettings.setEmployee(employee);
        reservationSettings.setAllowed(true);
        reservationSettingsDao.save(reservationSettings);

        ReservationSettings reservationSettingsResult = em.find(
                ReservationSettings.class, reservationSettings.getId()
        );
        assertEquals(true, reservationSettingsResult.getAllowed());
    }

    @Test
    public void update() {
        ReservationSettings reservationSettings = new ReservationSettings();
        Employee employee = new Employee();
        employee.setUsername("Username1");
        em.persist(employee);

        reservationSettings.setEmployee(employee);
        reservationSettings.setAllowed(false);
        em.persist(reservationSettings);

        ReservationSettings foundReservationSettings = em.find(
                ReservationSettings.class, reservationSettings.getId()
        );
        foundReservationSettings.setAllowed(true);
        reservationSettingsDao.update(foundReservationSettings);

        ReservationSettings reservationSettingsResult = em.find(
                ReservationSettings.class, reservationSettings.getId()
        );
        assertEquals(true, reservationSettingsResult.getAllowed());
    }

    @Test
    public void delete() {
        ReservationSettings reservationSettings = new ReservationSettings();
        Employee employee = new Employee();
        employee.setUsername("Username1");
        em.persist(employee);

        reservationSettings.setEmployee(employee);
        em.persist(reservationSettings);

        reservationSettingsDao.delete(reservationSettings.getId());

        ReservationSettings reservationSettingsResult = em.find(
                ReservationSettings.class, reservationSettings.getId()
        );
        assertNull(reservationSettingsResult);
    }

}
