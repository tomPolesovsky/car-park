package cz.pa165.carpark.service;

import cz.pa165.carpark.dao.EmployeeDao;
import cz.pa165.carpark.entity.Employee;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeDao employeeDao;

    @Inject
    public EmployeeServiceImpl (EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
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
