package cz.pa165.carpark.service.service;

import cz.pa165.carpark.persistence.dao.EmployeeDao;
import cz.pa165.carpark.persistence.entity.Employee;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    private PasswordEncoder passwordEncoder;

    @Inject
    public EmployeeServiceImpl(EmployeeDao employeeDao, PasswordEncoder passwordEncoder) {
        this.employeeDao = employeeDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeDao.findByUsername(username);
    }

    @Override
    public Employee find(Long id) {
        return employeeDao.find(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public void save(Employee employee) {
        if (employee.getPassword() != null) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        }
        employeeDao.save(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void delete(Long id) {
        employeeDao.delete(id);
    }

}
