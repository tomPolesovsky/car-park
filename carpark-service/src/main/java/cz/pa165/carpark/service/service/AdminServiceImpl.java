package cz.pa165.carpark.service.service;

import cz.pa165.carpark.persistence.dao.EmployeeDao;
import cz.pa165.carpark.persistence.entity.Employee;
import cz.pa165.carpark.service.exception.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * The admin facade's implementation.
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
@Service
public class AdminServiceImpl implements AdminService {

    private EmployeeDao employeeDao;

    private PasswordEncoder passwordEncoder;

    @Inject
    public AdminServiceImpl(EmployeeDao employeeDao, PasswordEncoder passwordEncoder) {
        this.employeeDao = employeeDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Employee login(String username, String password) {
        Employee employee = employeeDao.findByUsername(username);
        if (employee == null) {
            throw new AuthenticationException("Employee with username "+username+" was not found.");
        }

        String passwordHash = passwordEncoder.encode(password);
        if (!employee.getPassword().equals(passwordHash)) {
            throw new AuthenticationException("Password is not correct.");
        }

        return employee;
    }

}
