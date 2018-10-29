package cz.pa165.carpark.dao;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.ReservationSettings;
import cz.pa165.carpark.util.AbstractJUnitTest;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;

@Transactional
public class ReservationSettingsDaoImplTest extends AbstractJUnitTest {

    @Inject
    private ReservationSettingsDao reservationSettingsDao;

    @Test
    public void find() {
        ReservationSettings reservationSettings = new ReservationSettings();
        reservationSettings.setId(new Long(12345));
        em.persist(reservationSettings);

        ReservationSettings reservationSettingsResult = reservationSettingsDao.find(reservationSettings.getId());
        assertEquals(new Long(12345), reservationSettingsResult.getId());

        ReservationSettings reservationSettingsNull = reservationSettingsDao.find(10L);
        assertNull(reservationSettingsNull);
    }

    @Test
    public void findByEmployee() {
        ReservationSettings reservationSettings = new ReservationSettings();
        Employee employee = new Employee();
        Employee employee2 = new Employee();
        em.persist(employee);
        em.persist(employee2);

        reservationSettings.setEmployee(employee);
        em.persist(reservationSettings);

        ReservationSettings reservationSettingsResult = reservationSettingsDao.findByEmployee(
                reservationSettings.getEmployee()
        );

        assertEquals(employee, reservationSettingsResult.getEmployee());

        ReservationSettings reservationSettingsNull = reservationSettingsDao.findByEmployee(employee2);

        assertNull(reservationSettingsNull);
    }

    @Test
    public void findAll() {
        ReservationSettings firstReservationSettings = new ReservationSettings();
        firstReservationSettings.setId(new Long(12345));
        em.persist(firstReservationSettings);

        ReservationSettings secondReservationSettings = new ReservationSettings();
        secondReservationSettings.setId(new Long(54321));
        em.persist(secondReservationSettings);

        List<ReservationSettings> reservationSettingsResult = reservationSettingsDao.findAll();
        assertThat(reservationSettingsResult, hasSize(2));
        assertThat(reservationSettingsResult, contains(
                hasProperty("id",
                        equalTo(new Long(12345))),
                hasProperty("id",
                        equalTo(new Long(54321)))
        ));
    }

    @Test
    public void save() {
        ReservationSettings reservationSettings = new ReservationSettings();
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
        reservationSettings.setId(new Long(12345));
        em.persist(reservationSettings);

        reservationSettingsDao.delete(reservationSettings.getId());

        ReservationSettings reservationSettingsResult = em.find(
                ReservationSettings.class, reservationSettings.getId()
        );
        assertNull(reservationSettingsResult);
    }
}
