package cz.pa165.carpark.facade;

import cz.pa165.carpark.util.AbstractJUnitTest;
import cz.pa165.carpark.dto.EmployeeDTO;
import cz.pa165.carpark.dto.ReservationDTO;
import cz.pa165.carpark.dto.VehicleDTO;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import cz.pa165.carpark.entity.Vehicle;
import cz.pa165.carpark.service.VehicleService;
import cz.pa165.carpark.util.ObjectMapper;
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
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The tests for vehicle facade.
 *
 * @author Ondrej Svoren, 487558@mail.muni.cz
 */
public class VehicleFacadeTest extends AbstractJUnitTest {

    @Mock
    private ObjectMapper mapper;

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private VehicleFacadeImpl vehicleFacade;

    private Employee employee;

    private EmployeeDTO employeeDTO;

    private Vehicle vehicle;

    private VehicleDTO vehicleDTO;

    private Reservation reservation;

    private ReservationDTO reservationDTO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        employee = new Employee();
        employee.setId(1L);
        employee.setUsername("user1");
        employee.setFirstName("petr");
        employee.setLastName("novak");

        employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setUsername(employee.getUsername());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());

        vehicle = new Vehicle();
        vehicle.setId(2L);
        vehicle.setRegistrationNumber("1T2789");
        vehicle.setBrand("Skoda");
        vehicle.setColor("blue");
        vehicle.setMileage(1500L);
        vehicle.setType("Van");


        vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setRegistrationNumber(vehicle.getRegistrationNumber());
        vehicleDTO.setBrand(vehicle.getBrand());
        vehicleDTO.setColor(vehicle.getColor());
        vehicleDTO.setMileage(vehicle.getMileage());
        vehicleDTO.setType(vehicle.getType());

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setEmployee(employee);
        reservation.setFrom(LocalDateTime.of(2018, 1,1, 8, 0));
        reservation.setTo(LocalDateTime.of(2018, 1,10, 8, 0));
        reservation.setVehicle(vehicle);

        reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setEmployee(employeeDTO);
        reservationDTO.setFrom(reservation.getFrom());
        reservationDTO.setTo(reservation.getTo());
        reservationDTO.setVehicle(vehicleDTO);
    }

    @Test
    public void find() {
        when(vehicleService.find(any(Long.class)))
                .thenReturn(vehicle);
        when(mapper.mapTo(any(Vehicle.class), eq(VehicleDTO.class)))
                .thenReturn(vehicleDTO);

        VehicleDTO vehicleDTOResult = vehicleFacade.find(vehicle.getId());

        assertNotNull(vehicleDTOResult);
        assertThat(vehicleDTOResult.getBrand(), is(vehicle.getBrand()));
        assertThat(vehicleDTOResult.getColor(), is(vehicle.getColor()));
        assertThat(vehicleDTOResult.getMileage(), is(vehicle.getMileage()));
        assertThat(vehicleDTOResult.getType(), is(vehicle.getType()));
        assertThat(vehicleDTOResult.getRegistrationNumber(), is(vehicle.getRegistrationNumber()));
    }

    @Test
    public void findByRegistrationNumber() {
        when(vehicleService.findByRegistrationNumber(any(String.class)))
                .thenReturn(vehicle);
        when(mapper.mapTo(any(Vehicle.class), eq(VehicleDTO.class)))
                .thenReturn(vehicleDTO);

        VehicleDTO vehicleDTOResult = vehicleFacade.findByRegistrationNumber(vehicleDTO.getRegistrationNumber());

        assertNotNull(vehicleDTOResult);
    }

    @Test
    public void findAll() {
        when(vehicleService.findAll())
                .thenReturn(asList(vehicle));
        when(mapper.mapTo(anyCollection(), eq(VehicleDTO.class)))
                .thenReturn(asList(vehicleDTO));

        List<VehicleDTO> resultList = vehicleFacade.findAll();

        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
    }

    @Test
    public void create() {
        when(mapper.mapTo(any(VehicleDTO.class), eq(Vehicle.class))).thenReturn(vehicle);
        vehicleFacade.create(vehicleDTO);
        verify(vehicleService, times(1)).save(vehicle);
    }

    @Test
    public void update() {
        when(mapper.mapTo(any(Vehicle.class), eq(Vehicle.class)))
                .thenReturn(vehicle);
        when(mapper.mapTo(any(Vehicle.class), eq(VehicleDTO.class)))
                .thenReturn(vehicleDTO);

        VehicleDTO vehicleDTOResult = vehicleFacade.update(vehicleDTO);

        assertNotNull(vehicleDTOResult);
        assertThat(vehicleDTOResult.getRegistrationNumber(), is(vehicle.getRegistrationNumber()));
        assertThat(vehicleDTOResult.getType(), is(vehicle.getType()));
        assertThat(vehicleDTOResult.getColor(), is(vehicle.getColor()));
        assertThat(vehicleDTOResult.getMileage(), is(vehicle.getMileage()));
        assertThat(vehicleDTOResult.getBrand(), is(vehicle.getBrand()));

        verify(vehicleService, times(1)).update(vehicle);
    }

    @Test
    public void delete() {
        vehicleFacade.delete(vehicleDTO.getId());
        verify(vehicleService, times(1)).delete(vehicleDTO.getId());
    }

}
