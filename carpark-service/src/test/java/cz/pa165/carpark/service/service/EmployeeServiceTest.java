package cz.pa165.carpark.service.service;

import cz.pa165.carpark.persistence.dao.EmployeeDao;
import cz.pa165.carpark.persistence.entity.Employee;
import cz.pa165.carpark.service.util.AbstractJUnitTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * The tests for employee service.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
public class EmployeeServiceTest extends AbstractJUnitTest {

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employeePetr;

    private Employee employeeJana;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        employeePetr = new Employee();
        employeePetr.setId(1L);
        employeePetr.setUsername("petr");

        employeeJana = new Employee();
        employeeJana.setId(2L);
        employeeJana.setUsername("jana");
        employeeJana.setPosition("developer");
    }

    @Test
    public void find() {
        when(employeeDao.find(2L))
                .thenReturn(employeeJana);

        Employee employee = employeeService.find(2L);

        assertNotNull(employee);
        assertThat(employee.getUsername(), is(employeeJana.getUsername()));
        assertThat(employee.getPosition(), is(employeeJana.getPosition()));
        assertThat(employee.getId(), is(employeeJana.getId()));
        assertThat(employee.getId(), is(2L));
    }

    @Test
    public void findByUsername() {
        when(employeeDao.findByUsername("jana")).thenReturn(employeeJana);

        Employee employee = employeeService.findByUsername("jana");

        assertNotNull(employee);
        assertEquals(employeeJana, employee);
    }

    @Test
    public void findAll() {
        when(employeeDao.findAll())
                .thenReturn(asList(employeePetr, employeeJana));

        List<Employee> resultList = employeeService.findAll();

        assertNotNull(resultList);
        assertThat(resultList, hasSize(2));
        assertNotNull(resultList.get(0));
        assertThat(resultList.get(0).getUsername(), is("petr"));
        assertThat(resultList.get(1).getUsername(), is("jana"));
    }

    @Test
    public void save() {
        employeeService.save(employeeJana);
        verify(employeeDao, times(1)).save(any(Employee.class));
    }

    @Test
    public void update() {
        employeeService.update(employeeJana);
        verify(employeeDao, times(1)).update(employeeJana);
    }

    @Test
    public void delete() {
        employeeService.delete(employeeJana.getId());
        verify(employeeDao, times(1)).delete(employeeJana.getId());
    }

}
