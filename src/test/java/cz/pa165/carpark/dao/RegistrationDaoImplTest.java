package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import cz.pa165.carpark.entity.ReservationStatus;
import cz.pa165.carpark.entity.Vehicle;
import cz.pa165.carpark.util.AbstractJUnitTest;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static junit.framework.TestCase.assertNull;
import static org.hamcrest.Matchers.hasSize;

public class RegistrationDaoImplTest extends AbstractJUnitTest {

    @Inject
    private ReservationDao reservationDao;

    @Test
    public void find() {
        Reservation reservation = new Reservation();
        reservation.setId(12345);
        em.persist(reservation);

        Reservation reservationResult = reservationDao.find(reservation.getId());
        assertThat(12345, is(reservationResult.getId()));

        Reservation reservationNull = reservationDao.find(10L);
        assertNull(reservationNull);
    }

    @Test
    public void findByEmployee() {
        Reservation reservation = new Reservation();
        Employee employee = new Employee();
        Employee employee2 = new Employee();
        em.persist(employee);
        em.persist(employee2);

        reservation.setEmployee(employee);
        em.persist(reservation);

        Reservation reservationResult = reservationDao.findByEmployee(
                reservation.getEmployee(employee));

        assertThat(employee, equals(reservationResult.getEmployee()));

        Reservation reservationNull = reservationDao.findByEmployee(
                reservation.getEmployee(employee2);
        assertNull(reservationNull);
    }

    @Test
    public void findByVehicle() {
        Reservation reservation = new Reservation();
        Vehicle vehicle = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        em.persist(vehicle);
        em.persist(vehicle2);

        reservation.setVehicle(vehicle);
        em.persist(reservation);

        Reservation reservationResult = reservationDao.findByVehicle(
                reservation.getVehicle(vehicle));

        assertThat(vehicle, equals(reservationResult.getVehicle()));

        Reservation reservationNull = reservationDao.findByVehicle(
                reservation.getVehicle(vehicle2);
        assertNull(reservationNull);
    }

    @Test
    public void findAll() {
        Reservation firstReservation = new Reservation();
        firstReservation.setId(12345);
        em.persist(firstReservation);

        Reservation secondReservation = new Reservation();
        secondReservation.setId(54321);
        em.persist(secondReservation);

        List<Reservation> reservationResult = reservationDao.findAll();
        assertThat(reservationResult, hasSize(2));
        assertThat(reservationResult, contains(
                hasProperty("id",
                        is(12345)),
                hasProperty("id",
                        is(54321))
        ));
    }

    @Test
    public void save() {
        Reservation reservation = new Reservation();
        reservation.setStatus(ReservationStatus.NEW);
        reservationDao.save(reservation);

        Reservation reservationResult = em.find(Reservation.class, reservation.getId());
        assertEquals(ReservationStatus.NEW, reservationResult.getStatus());
    }

    @Test
    public void update() {
        Reservation reservation = new Reservation();
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
        reservation.setId(12345);
        em.persist(reservation);

        reservationDao.delete(reservation.getId());

        Reservation reservationResult = em.find(Reservation.class, reservation.getId());
        assertNull(reservationResult);
    }
}
