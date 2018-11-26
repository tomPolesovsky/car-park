package cz.pa165.carkpark.facade;

import cz.pa165.carkpark.util.AbstractJUnitTest;
import cz.pa165.carpark.dto.EmployeeDTO;
import cz.pa165.carpark.dto.ReservationDTO;
import cz.pa165.carpark.dto.ReservationSettingsDTO;
import cz.pa165.carpark.dto.VehicleDTO;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import cz.pa165.carpark.entity.Vehicle;
import cz.pa165.carpark.facade.EmployeeFacadeImpl;
import cz.pa165.carpark.service.EmployeeService;
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

public class EmployeeFacadeTest extends AbstractJUnitTest {

    @Mock
    private ObjectMapper mapper;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeFacadeImpl employeeFacade;

    private Employee employee;

    private EmployeeDTO employeeDTO;

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

        Vehicle vehicle = new Vehicle();
        vehicle.setId(2L);
        vehicle.setRegistrationNumber("1T2789");

        vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setRegistrationNumber(vehicle.getRegistrationNumber());

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
        when(employeeService.find(any(Long.class)))
                .thenReturn(employee);
        when(mapper.mapTo(any(Employee.class), eq(EmployeeDTO.class)))
                .thenReturn(employeeDTO);

        EmployeeDTO employeeDTOResult = employeeFacade.find(employee.getId());

        assertNotNull(employeeDTOResult);
        assertThat(employeeDTOResult.getUsername(), is(employee.getUsername()));
        assertThat(employeeDTOResult.getFirstName(), is(employee.getFirstName()));
        assertThat(employeeDTOResult.getLastName(), is(employee.getLastName()));
    }

    @Test
    public void findByUsername() {
        when(employeeService.findByUsername(any(String.class)))
                .thenReturn(employee);
        when(mapper.mapTo(any(Employee.class), eq(EmployeeDTO.class)))
                .thenReturn(employeeDTO);

        EmployeeDTO employeeDTOResult = employeeFacade.findByUsername(employeeDTO.getUsername());

        assertNotNull(employeeDTOResult);
    }

    @Test
    public void findAll() {
        when(employeeService.findAll())
                .thenReturn(asList(employee));
        when(mapper.mapTo(anyCollection(), eq(EmployeeDTO.class)))
                .thenReturn(asList(employeeDTO));

        List<EmployeeDTO> resultList = employeeFacade.findAll();

        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
    }

    @Test
    public void create() {
        when(mapper.mapTo(any(EmployeeDTO.class), eq(Employee.class))).thenReturn(employee);
        employeeFacade.create(employeeDTO);
        verify(employeeService, times(1)).save(employee);
    }

    @Test
    public void update() {
        when(mapper.mapTo(any(Employee.class), eq(Employee.class)))
                .thenReturn(employee);
        when(mapper.mapTo(any(Employee.class), eq(EmployeeDTO.class)))
                .thenReturn(employeeDTO);

        EmployeeDTO employeeDTOResult = employeeFacade.update(employeeDTO);

        assertNotNull(employeeDTOResult);
        assertThat(employeeDTOResult.getUsername(), is(employee.getUsername()));
        assertThat(employeeDTOResult.getFirstName(), is(employee.getFirstName()));
        assertThat(employeeDTOResult.getLastName(), is(employee.getLastName()));

        verify(employeeService, times(1)).update(employee);
    }

    @Test
    public void delete() {
        employeeFacade.delete(employeeDTO.getId());
        verify(employeeService, times(1)).delete(employeeDTO.getId());
    }

}
