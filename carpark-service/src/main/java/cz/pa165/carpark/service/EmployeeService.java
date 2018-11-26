package cz.pa165.carpark.service;
import cz.pa165.carpark.entity.Employee;
import java.util.List;

/**
 * The employee service's interface.
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
