package cz.pa165.carpark.service;
import cz.pa165.carpark.entity.Employee;
import java.util.List;

/**
 * The employee service's interface.
 *
 * @author Ondrej Svoren, 487558@mail.muni.cz
 */
public interface EmployeeService {

    Employee findByUsername(String username);

    Employee find(Long id);

    List<Employee> findAll();

    void save(Employee employee);

    void update(Employee employee);

    void delete(Long id);

}
