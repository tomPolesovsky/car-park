package cz.pa165.carkpark.facade;

import cz.pa165.carkpark.util.AbstractJUnitTest;
import cz.pa165.carpark.dto.EmployeeDTO;
import cz.pa165.carpark.dto.ReservationSettingsDTO;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.ReservationSettings;
import cz.pa165.carpark.facade.ReservationSettingsFacadeImpl;
import cz.pa165.carpark.service.ReservationSettingsService;
import cz.pa165.carpark.util.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The tests for reservation settings facade.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
public class ReservationSettingsFacadeTest extends AbstractJUnitTest {

    @Mock
    private ObjectMapper mapper;

    @Mock
    private ReservationSettingsService reservationSettingsService;

    @InjectMocks
    private ReservationSettingsFacadeImpl reservationSettingsFacade;

    private Employee employee;

    private Employee employee2;

    private EmployeeDTO employeeDTO;

    private EmployeeDTO employee2DTO;

    private ReservationSettings reservationSettings1;

    private ReservationSettingsDTO reservationSettings1DTO;

    private ReservationSettings reservationSettings2;

    private ReservationSettingsDTO reservationSettings2DTO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        employee = new Employee();
        employee.setId(1L);
        employee.setUsername("holahej");

        employee2 = new Employee();
        employee2.setId(2L);
        employee2.setUsername("ojojojoj");

        employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setUsername(employee.getUsername());

        employee2DTO = new EmployeeDTO();
        employee2DTO.setId(employee2.getId());
        employee2DTO.setUsername(employee2.getUsername());

        reservationSettings1 = new ReservationSettings();
        reservationSettings1.setId(1L);
        reservationSettings1.setEmployee(employee);

        reservationSettings2 = new ReservationSettings();
        reservationSettings2.setId(2L);
        reservationSettings2.setEmployee(employee2);

        reservationSettings1DTO = new ReservationSettingsDTO();
        reservationSettings1DTO.setId(reservationSettings1.getId());
        reservationSettings1DTO.setEmployee(employeeDTO);

        reservationSettings2DTO = new ReservationSettingsDTO();
        reservationSettings2DTO.setId(reservationSettings2.getId());
        reservationSettings2DTO.setEmployee(employee2DTO);
    }

    @Test
    public void find() {
        when(reservationSettingsService.find(any(Long.class)))
                .thenReturn(reservationSettings1);
        when(mapper.mapTo(any(ReservationSettings.class), eq(ReservationSettingsDTO.class)))
                .thenReturn(reservationSettings1DTO);

        ReservationSettingsDTO reservationSettingsDTOResult = reservationSettingsFacade.find(reservationSettings1.getId());

        assertNotNull(reservationSettingsDTOResult);
        assertThat(reservationSettingsDTOResult.getEmployee(), is(reservationSettings1.getEmployee()));
        assertThat(reservationSettingsDTOResult.getId(), is(reservationSettings1.getId()));

        assertNotNull(reservationSettingsDTOResult.getEmployee());
        assertThat(reservationSettingsDTOResult.getEmployee().getUsername(),
                is(reservationSettings1.getEmployee().getUsername()));
    }

    @Test
    public void findByEmployee() {
        when(reservationSettingsService.findByEmployee(any(Employee.class)))
                .thenReturn(reservationSettings1);
        when(mapper.mapTo(any(ReservationSettings.class), eq(ReservationSettingsDTO.class)))
                .thenReturn(reservationSettings1DTO);

        ReservationSettingsDTO settings = reservationSettingsFacade.findByEmployee(employeeDTO);

        assertNotNull(settings);
        assertEquals(settings, reservationSettings1DTO);
    }

    @Test
    public void findAll() {
        when(reservationSettingsService.findAll())
                .thenReturn(asList(reservationSettings1, reservationSettings2));
        when(mapper.mapTo(anyCollection(), eq(ReservationSettingsDTO.class)))
                .thenReturn(asList(reservationSettings1DTO, reservationSettings2DTO));

        List<ReservationSettingsDTO> resultList = reservationSettingsFacade.findAll();

        assertNotNull(resultList);
        assertThat(resultList, hasSize(2));
        assertNotNull(resultList.get(0));
        assertNotNull(resultList.get(0).getEmployee());
    }

    @Test
    public void create() {
        when(mapper.mapTo(any(ReservationSettingsDTO.class), eq(ReservationSettings.class))).thenReturn(reservationSettings1);
        reservationSettingsFacade.create(reservationSettings1DTO);
        verify(reservationSettingsService, times(1)).save(reservationSettings1);
    }

    @Test
    public void update() {
        when(mapper.mapTo(any(ReservationSettingsDTO.class), eq(ReservationSettings.class)))
                .thenReturn(reservationSettings1);
        when(mapper.mapTo(any(ReservationSettings.class), eq(ReservationSettingsDTO.class)))
                .thenReturn(reservationSettings1DTO);

        ReservationSettingsDTO reservationSettingsDTOResult = reservationSettingsFacade.update(reservationSettings1DTO);

        assertNotNull(reservationSettingsDTOResult);
        assertThat(reservationSettingsDTOResult.getEmployee(), is(reservationSettings1.getEmployee()));
        assertThat(reservationSettingsDTOResult.getId(), is(reservationSettings1.getId()));

        verify(reservationSettingsService, times(1)).update(reservationSettings1);
    }

    @Test
    public void delete() {
        when(mapper.mapTo(any(ReservationSettingsDTO.class), eq(ReservationSettings.class)))
                .thenReturn(reservationSettings1);
        reservationSettingsFacade.delete(reservationSettings1DTO.getId());
        verify(reservationSettingsService, times(1)).delete(reservationSettings1.getId());
    }
}
