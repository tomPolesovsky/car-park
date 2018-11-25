package cz.pa165.carkpark.service;

import cz.pa165.carkpark.util.AbstractJUnitTest;
import cz.pa165.carpark.dao.ReservationSettingsDao;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.ReservationSettings;
import cz.pa165.carpark.service.ReservationSettingsService;
import cz.pa165.carpark.service.ReservationSettingsServiceImpl;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The tests for reservation settings service.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
public class ReservationSettingsServiceTest extends AbstractJUnitTest {

    @Mock
    private ReservationSettingsDao reservationSettingsDao;

    @InjectMocks
    private ReservationSettingsServiceImpl reservationSettingsService;

    private Employee employeeSettings1;

    private Employee employeeSettings2;

    private ReservationSettings settings1;

    private ReservationSettings settings2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        employeeSettings1 = new Employee();
        employeeSettings1.setId(1L);
        employeeSettings1.setUsername("woko");

        employeeSettings2 = new Employee();
        employeeSettings2.setId(2L);
        employeeSettings2.setUsername("muhahaha");

        settings1 = new ReservationSettings();
        settings1.setId(1L);
        settings1.setEmployee(employeeSettings1);

        settings2 = new ReservationSettings();
        settings2.setId(2L);
        settings2.setEmployee(employeeSettings2);
    }

    @Test
    public void find() {
        when(reservationSettingsDao.find(2L))
                .thenReturn(settings2);

        ReservationSettings settings = reservationSettingsService.find(2L);

        assertNotNull(settings);
        assertNotNull(settings.getEmployee());
        assertThat(settings.getEmployee(), is(settings2.getEmployee()));
        assertThat(settings.getId(), is(settings2.getId()));
        assertThat(settings.getId(), is(2L));
    }

    @Test
    public void findByEmployee() {
        when(reservationSettingsDao.findByEmployee(employeeSettings2)).thenReturn(settings2);

        ReservationSettings settings = reservationSettingsService.findByEmployee(employeeSettings2);

        assertNotNull(settings);
        assertEquals(settings, settings2);
    }

    @Test
    public void findAll() {
        when(reservationSettingsDao.findAll())
                .thenReturn(asList(settings1, settings2));

        List<ReservationSettings> resultList = reservationSettingsService.findAll();

        assertNotNull(resultList);
        assertThat(resultList, hasSize(2));
        assertNotNull(resultList.get(0));
        assertNotNull(resultList.get(0).getEmployee());
        assertThat(resultList.get(0).getEmployee().getUsername(), is("woko"));
        assertThat(resultList.get(1).getEmployee().getUsername(), is("muhahaha"));
    }

    @Test
    public void save() {
        reservationSettingsService.save(settings2);
        verify(reservationSettingsDao, times(1)).save(any(ReservationSettings.class));
    }

    @Test
    public void update() {
        reservationSettingsService.update(settings2);
        verify(reservationSettingsDao, times(1)).update(settings2);
    }

    @Test
    public void delete() {
        reservationSettingsService.delete(settings2.getId());
        verify(reservationSettingsDao, times(1)).delete(settings2.getId());
    }
}
