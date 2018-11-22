package cz.pa165.carpark.service;
import cz.pa165.carpark.entity.Employee;
import java.util.List;

public interface EmployeeService {

    Employee findByUsername(String username);

    Employee find(Long id);

    List<Employee> findAll();

    void save(Employee employee);

    void update(Employee employee);

    void delete(Long id);

}
