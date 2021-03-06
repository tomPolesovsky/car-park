package cz.pa165.carpark.service.service;

import cz.pa165.carpark.persistence.entity.Employee;

import java.util.List;

/**
 * The employee service's interface.
 * Possibility to save, update, delete, find, find by username, find all (employee/s) via this service.
 *
 * @author Ondrej Svoren, 487558@mail.muni.cz
 */
public interface EmployeeService {

    /**
     * Find employee for the specified username
     *
     * @param username
     * @return employee
     */
    Employee findByUsername(String username);

    /**
     * Find the employee with the specified id
     *
     * @param id unambiguous identification of entity
     * @return employee
     */
    Employee find(Long id);

    /**
     * Find all employees
     *
     * @return list of all employees
     */
    List<Employee> findAll();

    /**
     * Save the employee
     *
     * @param employee
     */
    void save(Employee employee);

    /**
     * Update the specified employee
     *
     * @param employee
     */
    void update(Employee employee);

    /**
     * Delete the employee with the specified id
     *
     * @param id unambiguous identification of entity
     */
    void delete(Long id);

}
