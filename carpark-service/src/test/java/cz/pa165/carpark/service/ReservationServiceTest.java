package cz.pa165.carpark.service;

import cz.pa165.carpark.util.AbstractJUnitTest;
import cz.pa165.carpark.dao.ReservationDao;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import cz.pa165.carpark.entity.ReservationSettings;
import cz.pa165.carpark.entity.Vehicle;
import cz.pa165.carpark.enums.ReservationStatus;
import cz.pa165.carpark.exception.UnavailableCarException;
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
import static org.junit.Assert.*;
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

    private Reservation reservationFirst;

    private Reservation reservationSecond;

    private Reservation reservationThird;

    private LocalDateTime defaultDateTime;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        employee = new Employee();
        employee.setId(1L);
        employee.setUsername("petr");
        employee.setFirstName("Petr");
        employee.setLastName("Pavel");

        vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setRegistrationNumber("1T2789");

        defaultDateTime = LocalDateTime.of(2018, 1, 1, 8, 0);
        reservationFirst = new Reservation();
        reservationFirst.setId(1L);
        reservationFirst.setFrom(defaultDateTime.plusDays(2));
        reservationFirst.setTo(defaultDateTime.plusDays(4));
        reservationFirst.setEmployee(employee);
        reservationFirst.setVehicle(vehicle);

        Vehicle vehicleSecond = new Vehicle();
        vehicleSecond.setId(2L);
        vehicleSecond.setRegistrationNumber("5A2759");

        reservationSecond = new Reservation();
        reservationSecond.setId(2L);
        reservationSecond.setFrom(defaultDateTime.plusDays(6));
        reservationSecond.setTo(defaultDateTime.plusDays(8));
        reservationSecond.setEmployee(employee);
        reservationSecond.setVehicle(vehicleSecond);

        Employee employeeSecond = new Employee();
        employeeSecond.setId(2L);
        employeeSecond.setUsername("petr2");
        employeeSecond.setFirstName("Petr");
        employeeSecond.setLastName("Novák");

        reservationThird = new Reservation();
        reservationThird.setId(3L);
        reservationThird.setFrom(defaultDateTime.plusDays(10));
        reservationThird.setTo(defaultDateTime.plusDays(12));
        reservationThird.setEmployee(employeeSecond);
        reservationThird.setVehicle(vehicle);
    }

    @Test
    public void find() {
        when(reservationDao.find(any(Long.class)))
                .thenReturn(reservationFirst);

        Reservation reservationResult = reservationService.find(1L);

        assertNotNull(reservationResult);
        assertThat(reservationResult.getFrom(), is(reservationFirst.getFrom()));
        assertThat(reservationResult.getTo(), is(reservationFirst.getTo()));

        assertNotNull(reservationResult.getEmployee());
        assertThat(reservationResult.getEmployee().getUsername(),
                is(reservationFirst.getEmployee().getUsername()));
    }

    @Test
    public void findByEmployee() {
        when(reservationDao.findByEmployee(any(Employee.class)))
                .thenReturn(asList(reservationFirst));

        List<Reservation> resultList = reservationService.findByEmployee(employee);

        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
    }

    @Test
    public void findByVehicle() {
        when(reservationDao.findByVehicle(any(Vehicle.class)))
                .thenReturn(asList(reservationFirst));

        List<Reservation> resultList = reservationService.findByVehicle(vehicle);

        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
    }

    @Test
    public void findAll() {
        when(reservationDao.findAll())
                .thenReturn(asList(reservationFirst));

        List<Reservation> resultList = reservationService.findAll();

        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
    }

    @Test(expected = UnavailableCarException.class)
    public void processRequestNotAvailable() {
        when(reservationDao.findAll())
                .thenReturn(asList(reservationFirst, reservationSecond, reservationThird));
        when(reservationDao.isVehicleAvailable(any(Vehicle.class), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(false);

        reservationService.processRequest(new Reservation(), new ReservationSettings());
    }

    @Test
    public void processRequest() {
        when(reservationDao.findAll())
                .thenReturn(asList(reservationFirst, reservationSecond, reservationThird));
        when(reservationDao.isVehicleAvailable(any(Vehicle.class), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(true);

        Reservation reservation = new Reservation();
        ReservationSettings settings = new ReservationSettings();
        settings.setAutoApproval(true);
        reservationService.processRequest(reservation, settings);
        assertThat(reservation.getStatus(), is(ReservationStatus.APPROVED));

        reservation = new Reservation();
        settings = new ReservationSettings();
        settings.setAutoApproval(false);
        reservationService.processRequest(new Reservation(), settings);
        assertNotNull(reservation);
        assertThat(reservation.getStatus(), is(ReservationStatus.NEW));

        reservation = new Reservation();
        settings = new ReservationSettings();
        settings.setAutoApproval(false);
        settings.setAllowed(false);
        reservationService.processRequest(reservation, settings);
        assertNotNull(reservation);
        assertThat(reservation.getStatus(), is(ReservationStatus.CANCELED));
    }

    @Test
    public void acceptOrDecline() {
        Reservation reservation = new Reservation();
        reservationService.acceptOrDecline(reservation, true);
        assertThat(reservation.getStatus(), is(ReservationStatus.APPROVED));

        reservation = new Reservation();
        reservationService.acceptOrDecline(reservation, false);
        assertThat(reservation.getStatus(), is(ReservationStatus.CANCELED));
    }

    @Test
    public void update() {
        reservationService.update(reservationFirst);
        verify(reservationDao, times(1)).update(reservationFirst);
    }

    @Test
    public void delete() {
        reservationService.delete(reservationFirst.getId());
        verify(reservationDao, times(1)).delete(reservationFirst.getId());
    }

    @Test
    public void filterInterval() {
        when(reservationDao.findAll())
                .thenReturn(asList(reservationFirst, reservationSecond, reservationThird));

        ReservationFilterParams params = new ReservationFilterParams();
        params.setQuery("petr");
        params.setFrom(defaultDateTime);
        params.setTo(defaultDateTime.plusDays(14));
        List<Reservation> resultList = reservationService.filter(params);
        assertNotNull(resultList);
        assertThat(resultList, hasSize(3));

        params = new ReservationFilterParams();
        params.setQuery("petr");
        params.setFrom(defaultDateTime.plusDays(3));
        params.setTo(defaultDateTime.plusDays(14));
        resultList = reservationService.filter(params);
        assertNotNull(resultList);
        assertThat(resultList, hasSize(2));

        params = new ReservationFilterParams();
        params.setQuery("petr");
        params.setFrom(defaultDateTime.plusDays(9));
        params.setTo(defaultDateTime.plusDays(14));
        resultList = reservationService.filter(params);
        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));

        params = new ReservationFilterParams();
        params.setFrom(defaultDateTime.plusDays(14));
        params.setTo(defaultDateTime.plusDays(20));
        resultList = reservationService.filter(params);
        assertNotNull(resultList);
        assertThat(resultList, hasSize(0));
    }

    @Test
    public void filterQuery() {
        when(reservationDao.findAll())
                .thenReturn(asList(reservationFirst, reservationSecond, reservationThird));

        ReservationFilterParams params = new ReservationFilterParams();
        params.setQuery("Pavel");
        params.setFrom(defaultDateTime);
        params.setTo(defaultDateTime.plusDays(20));
        List<Reservation> resultList = reservationService.filter(params);
        assertNotNull(resultList);
        assertThat(resultList, hasSize(2));

        params = new ReservationFilterParams();
        params.setQuery("novák");
        params.setFrom(defaultDateTime);
        params.setTo(defaultDateTime.plusDays(20));
        resultList = reservationService.filter(params);
        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));

        params = new ReservationFilterParams();
        params.setQuery("1T2789");
        params.setFrom(defaultDateTime);
        params.setTo(defaultDateTime.plusDays(20));
        resultList = reservationService.filter(params);
        assertNotNull(resultList);
        assertThat(resultList, hasSize(2));

        params = new ReservationFilterParams();
        params.setQuery("5A2759");
        params.setFrom(defaultDateTime);
        params.setTo(defaultDateTime.plusDays(20));
        resultList = reservationService.filter(params);
        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
    }

    @Test
    public void filterPagination() {
        when(reservationDao.findAll())
                .thenReturn(asList(reservationFirst, reservationSecond, reservationThird));

        ReservationFilterParams params = new ReservationFilterParams();
        params.setQuery("Petr");
        params.setFrom(defaultDateTime);
        params.setTo(defaultDateTime.plusDays(20));
        params.setPage(1);
        params.setPageSize(1);
        List<Reservation> resultList = reservationService.filter(params);
        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
        assertThat(resultList.get(0).getId(), is(1L));

        params.setPage(2);
        resultList = reservationService.filter(params);
        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
        assertThat(resultList.get(0).getId(), is(2L));

        params.setPage(3);
        resultList = reservationService.filter(params);
        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
        assertThat(resultList.get(0).getId(), is(3L));

        params = new ReservationFilterParams();
        params.setQuery("Petr");
        params.setFrom(defaultDateTime);
        params.setTo(defaultDateTime.plusDays(20));
        params.setPage(1);
        params.setPageSize(3);
        resultList = reservationService.filter(params);
        assertNotNull(resultList);
        assertThat(resultList, hasSize(3));
        assertThat(resultList.get(0).getId(), is(1L));
        assertThat(resultList.get(1).getId(), is(2L));
        assertThat(resultList.get(2).getId(), is(3L));

        params.setPage(2);
        resultList = reservationService.filter(params);
        assertNotNull(resultList);
        assertTrue(resultList.isEmpty());
    }

}
