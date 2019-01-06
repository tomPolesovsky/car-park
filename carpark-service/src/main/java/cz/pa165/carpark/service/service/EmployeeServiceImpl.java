package cz.pa165.carpark.service.service;

import cz.pa165.carpark.persistence.dao.EmployeeDao;
import cz.pa165.carpark.persistence.entity.Employee;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * The employee' service.
 *
 * @author Ondrej Svoren, 487558@mail.muni.cz
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    private PasswordEncoder passwordEncoder;

    @Inject
    public EmployeeServiceImpl(EmployeeDao employeeDao, PasswordEncoder passwordEncoder) {
        this.employeeDao = employeeDao;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Find employee for the specified username
     *
     * @param username
     * @return employee
     */
    @Override
    public Employee findByUsername(String username) {
        return employeeDao.findByUsername(username);
    }

    /**
     * Find the employee with the specified id
     *
     * @param id unambiguous identification of entity
     * @return employee
     */
    @Override
    public Employee find(Long id) {
        return employeeDao.find(id);
    }

    /**
     * Find all employees
     *
     * @return list of all employees
     */
    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    /**
     * Save the employee
     *
     * @param employee
     */
    @Override
    public void save(Employee employee) {
        if (employee.getPassword() != null) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        }
        employeeDao.save(employee);
    }

    /**
     * Update the specified employee
     *
     * @param employee
     */
    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    /**
     * Delete the employee with the specified id
     *
     * @param id unambiguous identification of entity
     */
    @Override
    public void delete(Long id) {
        employeeDao.delete(id);
    }

}
