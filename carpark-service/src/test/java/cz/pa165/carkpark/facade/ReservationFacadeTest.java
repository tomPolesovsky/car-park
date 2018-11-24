package cz.pa165.carkpark.facade;

import cz.pa165.carkpark.util.AbstractJUnitTest;
import cz.pa165.carpark.dto.EmployeeDTO;
import cz.pa165.carpark.dto.ReservationDTO;
import cz.pa165.carpark.dto.VehicleDTO;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import cz.pa165.carpark.entity.Vehicle;
import cz.pa165.carpark.facade.ReservationFacadeImpl;
import cz.pa165.carpark.service.ReservationService;
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

public class ReservationFacadeTest extends AbstractJUnitTest {

    @Mock
    private ObjectMapper mapper;

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationFacadeImpl reservationFacade;

    private EmployeeDTO employeeDTO;

    private VehicleDTO vehicleDTO;

    private Reservation reservation;

    private ReservationDTO reservationDTO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setUsername("petr");

        employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setUsername(employee.getUsername());

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
        when(reservationService.find(any(Long.class)))
                .thenReturn(reservation);
        when(mapper.mapTo(any(Reservation.class), eq(ReservationDTO.class)))
                .thenReturn(reservationDTO);

        ReservationDTO reservationDTOResult = reservationFacade.find(reservation.getId());

        assertNotNull(reservationDTOResult);
        assertThat(reservationDTOResult.getFrom(), is(reservation.getFrom()));
        assertThat(reservationDTOResult.getTo(), is(reservation.getTo()));

        assertNotNull(reservationDTOResult.getEmployee());
        assertThat(reservationDTOResult.getEmployee().getUsername(),
                is(reservation.getEmployee().getUsername()));
    }

    @Test
    public void findByEmployee() {
        when(reservationService.findByEmployee(any(Employee.class)))
                .thenReturn(asList(reservation));
        when(mapper.mapTo(anyCollection(), eq(ReservationDTO.class)))
                .thenReturn(asList(reservationDTO));

        List<ReservationDTO> resultList = reservationFacade.findByEmployee(employeeDTO);

        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
    }

    @Test
    public void findByVehicle() {
        when(reservationService.findByVehicle(any(Vehicle.class)))
                .thenReturn(asList(reservation));
        when(mapper.mapTo(anyCollection(), eq(ReservationDTO.class)))
                .thenReturn(asList(reservationDTO));

        List<ReservationDTO> resultList = reservationFacade.findByVehicle(vehicleDTO);

        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
    }

    @Test
    public void findAll() {
        when(reservationService.findAll())
                .thenReturn(asList(reservation));
        when(mapper.mapTo(anyCollection(), eq(ReservationDTO.class)))
                .thenReturn(asList(reservationDTO));

        List<ReservationDTO> resultList = reservationFacade.findAll();

        assertNotNull(resultList);
        assertThat(resultList, hasSize(1));
    }

    // todo: new reservation

    @Test
    public void update() {
        when(mapper.mapTo(any(Reservation.class), eq(Reservation.class)))
                .thenReturn(reservation);
        when(mapper.mapTo(any(Reservation.class), eq(ReservationDTO.class)))
                .thenReturn(reservationDTO);

        ReservationDTO reservationDTOResult = reservationFacade.update(reservationDTO);

        assertNotNull(reservationDTOResult);
        assertThat(reservationDTOResult.getFrom(), is(reservation.getFrom()));
        assertThat(reservationDTOResult.getTo(), is(reservation.getTo()));

        verify(reservationService, times(1)).update(reservation);
    }

    @Test
    public void delete() {
        reservationFacade.delete(reservationDTO.getId());
        verify(reservationService, times(1)).delete(reservation.getId());
    }

}
