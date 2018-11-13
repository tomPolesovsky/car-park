package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.util.AbstractJUnitTest;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Tests for Employee' DAO implementation.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@Transactional
public class EmployeeDaoImplTest extends AbstractJUnitTest {

    /**
     * Employee' DAO injected
     */
    @Inject
    private EmployeeDao employeeDao;

    /**
     * Test the find method in DAO.
     * Will be persisted employee found and how about employee with negative id?
     */
    @Test
    public void find() {
        Employee employee = new Employee();
        employee.setUsername("yokoono");
        em.persist(employee);

        Employee employeeResult = employeeDao.find(employee.getId());
        assertThat("yokoono", is(employeeResult.getUsername()));

        Employee employeeNull = employeeDao.find(-10L);
        assertNull(employeeNull);
    }

    /**
     * Test the findByUsername method in DAO.
     * Will be persisted employee found and how about employee that does not exist?
     */
    @Test
    public void findByUsername() {
        Employee employee = new Employee();
        employee.setUsername("yokoono");
        em.persist(employee);

        Employee employeeResult = employeeDao.findByUsername(
                employee.getUsername()
        );
        assertThat("yokoono", is(employeeResult.getUsername()));
    }

    /**
     * Tests the findByUsername method in Dao.
     * The username wakawaka doesn't exists
     */
    @Test(expected = DataAccessException.class)
    public void findByUsernameNotExists() {
        employeeDao.findByUsername("wakawaka");
    }

    /**
     * Test the findAll method in DAO.
     * Will we find all persisted employees?
     */
    @Test
    public void findAll() {
        Employee firstEmployee = new Employee();
        firstEmployee.setUsername("yokoono");
        em.persist(firstEmployee);

        Employee secondEmployee = new Employee();
        secondEmployee.setUsername("wakawaka");
        em.persist(secondEmployee);

        List<Employee> employeeList = employeeDao.findAll();
        assertThat(employeeList, hasSize(2));
        assertThat(employeeList, contains(
                hasProperty("username",
                        is("yokoono")),
                hasProperty("username",
                        is("wakawaka"))
        ));
    }

    /**
     * Test the save method in DAO.
     * Will be found saved employee?
     */
    @Test
    public void save() {
        Employee employee = new Employee();
        employee.setUsername("yokoono");
        employeeDao.save(employee);

        Employee employeeResult = em.find(Employee.class, employee.getId());
        assertEquals("yokoono", employeeResult.getUsername());
    }

    /**
     * Test the update method in DAO.
     * Will be found updated employee?
     */
    @Test
    public void update() {
        Employee employee = new Employee();
        employee.setUsername("yokoono");
        employee.setFirstName("Yoko");
        employee.setLastName("Ono");
        em.persist(employee);

        Employee employeeResultOne = em.find(Employee.class, employee.getId());
        employeeResultOne.setFirstName("Waka");
        employeeDao.update(employeeResultOne);

        Employee employeeResultTwo = em.find(Employee.class, employee.getId());
        assertEquals("Waka", employeeResultTwo.getFirstName());
    }

    /**
     * Test the delete method in DAO.
     * Will be found deleted employee?
     */
    @Test
    public void delete() {
        Employee employee = new Employee();
        employee.setUsername("yokoono");
        em.persist(employee);

        employeeDao.delete(employee.getId());

        Employee employeeResult = em.find(Employee.class, employee.getId());
        assertNull(employeeResult);
    }
}
