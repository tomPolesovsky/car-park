package cz.pa165.carkpark.service;

import cz.pa165.carkpark.util.AbstractJUnitTest;
import cz.pa165.carpark.dao.ReservationDao;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import cz.pa165.carpark.entity.Vehicle;
import cz.pa165.carpark.service.ReservationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests for Reservation' service implementation.
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
public class ReservationServiceTest extends AbstractJUnitTest {

    @Mock
    private ReservationDao reservationDao;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Vehicle vehicle;

    private Employee employee;

    private Reservation reservation;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        employee = new Employee();
        employee.setId(1L);
        employee.setUsername("petr");

        vehicle = new Vehicle();
        vehicle.setId(2L);
        vehicle.setRegistrationNumber("1T2789");

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setEmployee(employee);
        reservation.setFrom(LocalDateTime.of(2018, 1, 1, 8, 0));
        reservation.setTo(LocalDateTime.of(2018, 1, 10, 8, 0));
        reservation.setVehicle(vehicle);
    }

    @Test
    public void find() {
        when(reservationDao.find(any(Long.class)))
                .thenReturn(reservation);

        Reservation reservationResult = reservationDao.find(1L);

        assertNotNull(reservationResult);
        assertThat(reservationResult.getFrom(), is(reservation.getFrom()));
        assertThat(reservationResult.getTo(), is(reservation.getTo()));

        assertNotNull(reservationResult.getEmployee());
        assertThat(reservationResult.getEmployee().getUsername(),
                is(reservation.getEmployee().getUsername()));
    }

    @Test
    public void findByEmployee() {
        when(reservationDao.findByEmployee(any(Employee.class)))
                .thenReturn(asList(reservation));

        List<Reservation> resultList = reservationService.findByEmployee(employee);

        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
    }

    @Test
    public void findByVehicle() {
        when(reservationDao.findByVehicle(any(Vehicle.class)))
                .thenReturn(asList(reservation));

        List<Reservation> resultList = reservationService.findByVehicle(vehicle);

        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
    }

    @Test
    public void findAll() {
        when(reservationDao.findAll())
                .thenReturn(asList(reservation));

        List<Reservation> resultList = reservationService.findAll();

        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
    }

    // todo: new reservation

    @Test
    public void update() {
        reservationService.update(reservation);
        verify(reservationDao, times(1)).update(reservation);
    }

    @Test
    public void delete() {
        reservationService.delete(reservation.getId());
        verify(reservationDao, times(1)).delete(reservation.getId());
    }

}
