package cz.pa165.carpark.service;

import cz.pa165.carpark.dao.VehicleDao;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import cz.pa165.carpark.entity.Vehicle;
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
 * The tests for vehicle service.
 *
 * @author Ondrej Svoren, 487558@mail.muni.cz
 */
public class VehicleServiceTest {

    @Mock
    private VehicleDao vehicleDao;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

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
        vehicle.setBrand("Skoda");
        vehicle.setColor("blue");
        vehicle.setMileage(1500L);
        vehicle.setType("Van");

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setEmployee(employee);
        reservation.setFrom(LocalDateTime.of(2018, 1, 1, 8, 0));
        reservation.setTo(LocalDateTime.of(2018, 1, 10, 8, 0));
        reservation.setVehicle(vehicle);
    }

    @Test
    public void find() {
        when(vehicleDao.find(any(Long.class)))
                .thenReturn(vehicle);

        Vehicle vehicleResult = vehicleDao.find(1L);

        assertNotNull(vehicleResult);
        assertThat(vehicleResult.getBrand(), is(vehicle.getBrand()));
        assertThat(vehicleResult.getColor(), is(vehicle.getColor()));
        assertThat(vehicleResult.getMileage(), is(vehicle.getMileage()));
        assertThat(vehicleResult.getType(), is(vehicle.getType()));
        assertThat(vehicleResult.getRegistrationNumber(), is(vehicle.getRegistrationNumber()));
    }

    @Test
    public void findByRegistrationNumber() {
        when(vehicleDao.findByRegistrationNumber(any(String.class)))
                .thenReturn(vehicle);

        Vehicle vehicleResult = vehicleService.findByRegistrationNumber(vehicle.getRegistrationNumber());

        assertNotNull(vehicleResult);
    }

    @Test
    public void findAll() {
        when(vehicleDao.findAll())
                .thenReturn(asList(vehicle));

        List<Vehicle> resultList = vehicleService.findAll();

        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
    }

    // todo: new vehicle

    @Test
    public void update() {
        vehicleService.update(vehicle);
        verify(vehicleDao, times(1)).update(vehicle);
    }

    @Test
    public void delete() {
        vehicleService.delete(vehicle.getId());
        verify(vehicleDao, times(1)).delete(vehicle.getId());
    }

}
