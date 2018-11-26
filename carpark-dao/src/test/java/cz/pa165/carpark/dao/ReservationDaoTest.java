package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import cz.pa165.carpark.enums.ReservationStatus;
import cz.pa165.carpark.entity.Vehicle;
import cz.pa165.carpark.util.AbstractJUnitTest;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

/**
 * Tests for Reservation' DAO implementation.
 *
 * @author Ondrej Svoreň
 */
@Transactional
public class ReservationDaoTest extends AbstractJUnitTest {

    @Inject
    private ReservationDao reservationDao;

    @Test
    public void findByEmployee() {
        Reservation reservation = new Reservation();

        Employee employee = new Employee();
        employee.setUsername("Username");
        em.persist(employee);

        Employee employee2 = new Employee();
        employee2.setUsername("Username2");
        em.persist(employee2);

        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber("ABC123");
        em.persist(vehicle);

        reservation.setFrom(LocalDateTime.of(2018, Month.JANUARY, 1, 10, 10, 30));
        reservation.setTo(LocalDateTime.of(2018, Month.FEBRUARY, 1, 10, 10, 30));

        reservation.setEmployee(employee);
        reservation.setVehicle(vehicle);
        em.persist(reservation);

        List<Reservation> reservationResult = reservationDao.findByEmployee(
                reservation.getEmployee()
        );

        assertThat(reservationResult, hasSize(1));
        assertThat(reservationResult, contains(
                hasProperty("employee", equalTo(employee))
        ));

        List<Reservation> reservationNull = reservationDao.findByEmployee(employee2);

        assertThat(reservationNull, is(empty()));
    }

    @Test
    public void findByVehicle() {
        Reservation reservation = new Reservation();

        Employee employee = new Employee();
        employee.setUsername("Username");
        em.persist(employee);

        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber("ABC123");
        em.persist(vehicle);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setRegistrationNumber("123ABC");
        em.persist(vehicle2);

        reservation.setVehicle(vehicle);
        reservation.setEmployee(employee);
        reservation.setFrom(LocalDateTime.of(2018, Month.JANUARY, 1, 10, 10, 30));
        reservation.setTo(LocalDateTime.of(2018, Month.FEBRUARY, 1, 10, 10, 30));
        em.persist(reservation);

        List<Reservation> reservationResult = reservationDao.findByVehicle(
                reservation.getVehicle()
        );

        assertThat(reservationResult, hasSize(1));
        assertThat(reservationResult, contains(
                hasProperty("vehicle", equalTo(vehicle))
        ));

        List<Reservation> reservationNull = reservationDao.findByVehicle(vehicle2);

        assertThat(reservationNull, is(empty()));
    }

    @Test
    public void findAll() {
        Reservation firstReservation = new Reservation();

        Employee firstEmployee = new Employee();
        firstEmployee.setUsername("Username1");
        em.persist(firstEmployee);

        Vehicle firstVehicle = new Vehicle();
        firstVehicle.setRegistrationNumber("ABC123");
        em.persist(firstVehicle);

        firstReservation.setFrom(LocalDateTime.of(2018, Month.JANUARY, 1, 10, 10, 30));
        firstReservation.setTo(LocalDateTime.of(2018, Month.FEBRUARY, 1, 10, 10, 30));

        firstReservation.setEmployee(firstEmployee);
        firstReservation.setVehicle(firstVehicle);
        em.persist(firstReservation);

        Reservation secondReservation = new Reservation();

        Employee secondEmployee = new Employee();
        secondEmployee.setUsername("Username2");
        em.persist(secondEmployee);

        Vehicle secondVehicle = new Vehicle();
        secondVehicle.setRegistrationNumber("123ABC");
        em.persist(secondVehicle);

        secondReservation.setFrom(LocalDateTime.of(2018, Month.JANUARY, 1, 10, 10, 30));
        secondReservation.setTo(LocalDateTime.of(2018, Month.FEBRUARY, 1, 10, 10, 30));

        secondReservation.setEmployee(secondEmployee);
        secondReservation.setVehicle(secondVehicle);
        em.persist(secondReservation);

        List<Reservation> reservationResult = reservationDao.findAll();
        assertThat(reservationResult, hasSize(2));
        assertThat(reservationResult, contains(
                hasProperty("vehicle",
                        equalTo(firstVehicle)),
                hasProperty("vehicle",
                        equalTo(secondVehicle))
        ));
    }

    @Test
    public void save() {
        Reservation reservation = new Reservation();
        Employee employee = new Employee();
        employee.setUsername("Username");
        em.persist(employee);

        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber("ABC123");
        em.persist(vehicle);

        reservation.setFrom(LocalDateTime.of(2018, Month.JANUARY, 1, 10, 10, 30));
        reservation.setTo(LocalDateTime.of(2018, Month.FEBRUARY, 1, 10, 10, 30));

        reservation.setEmployee(employee);
        reservation.setVehicle(vehicle);
        reservation.setStatus(ReservationStatus.NEW);

        reservationDao.save(reservation);

        Reservation reservationResult = em.find(Reservation.class, reservation.getId());
        assertEquals(ReservationStatus.NEW, reservationResult.getStatus());
    }

    @Test
    public void update() {
        Reservation reservation = new Reservation();
        Employee employee = new Employee();
        employee.setUsername("Username");
        em.persist(employee);

        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber("ABC123");
        em.persist(vehicle);

        reservation.setFrom(LocalDateTime.of(2018, Month.JANUARY, 1, 10, 10, 30));
        reservation.setTo(LocalDateTime.of(2018, Month.FEBRUARY, 1, 10, 10, 30));

        reservation.setEmployee(employee);
        reservation.setVehicle(vehicle);

        reservation.setStatus(ReservationStatus.NEW);
        em.persist(reservation);

        Reservation foundReservation = em.find(Reservation.class, reservation.getId());
        foundReservation.setStatus(ReservationStatus.APPROVED);
        reservationDao.update(foundReservation);

        Reservation reservationResult = em.find(Reservation.class, reservation.getId());
        assertEquals(ReservationStatus.APPROVED, reservationResult.getStatus());
    }

    @Test
    public void delete() {
        Reservation reservation = new Reservation();

        Employee employee = new Employee();
        employee.setUsername("Username");
        em.persist(employee);

        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber("ABC123");
        em.persist(vehicle);

        reservation.setFrom(LocalDateTime.of(2018, Month.JANUARY, 1, 10, 10, 30));
        reservation.setTo(LocalDateTime.of(2018, Month.FEBRUARY, 1, 10, 10, 30));

        reservation.setEmployee(employee);
        reservation.setVehicle(vehicle);
        em.persist(reservation);

        reservationDao.delete(reservation.getId());

        Reservation reservationResult = em.find(Reservation.class, reservation.getId());
        assertNull(reservationResult);
    }

    @Test
    public void isVehicleAvailable() {
        LocalDateTime defaultValue = LocalDateTime.of(2018, Month.JANUARY, 1, 8, 0);

        Employee employee = new Employee();
        employee.setUsername("Username");
        em.persist(employee);

        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber("ABC123");
        em.persist(vehicle);

        Reservation reservation = new Reservation();
        reservation.setFrom(defaultValue);
        reservation.setTo(defaultValue.plusMonths(1));
        reservation.setVehicle(vehicle);
        reservation.setEmployee(employee);
        em.persist(reservation);

        assertFalse(reservationDao.isVehicleAvailable(vehicle, defaultValue.plusDays(5), defaultValue.plusDays(10)));
        assertFalse(reservationDao.isVehicleAvailable(vehicle, defaultValue.plusDays(5), defaultValue.plusMonths(2)));
        assertTrue(reservationDao.isVehicleAvailable(vehicle, defaultValue.plusMonths(2), defaultValue.plusMonths(3)));
    }

}
