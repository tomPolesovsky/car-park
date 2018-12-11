package cz.pa165.carpark.service.facade;

import cz.pa165.carpark.api.dto.EmployeeDTO;
import cz.pa165.carpark.api.facade.EmployeeFacade;
import cz.pa165.carpark.persistence.entity.Employee;
import cz.pa165.carpark.service.service.EmployeeService;
import cz.pa165.carpark.service.util.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * The employee facade's implementation.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@Transactional
@Service
public class EmployeeFacadeImpl implements EmployeeFacade {

    private ObjectMapper objectMapper;

    private EmployeeService employeeService;

    @Inject
    public EmployeeFacadeImpl(ObjectMapper objectMapper, EmployeeService employeeService) {
        this.objectMapper = objectMapper;
        this.employeeService = employeeService;
    }

    /**
     * Find the employee with the specified id
     *
     * @param id unambiguous identification of entity
     * @return employee dto
     */
    @Override
    public EmployeeDTO find(Long id) {
        Employee employee = employeeService.find(id);
        return (employee == null) ? null : objectMapper.mapTo(employee, EmployeeDTO.class);
    }

    /**
     * Find employee with the specified username
     *
     * @param username
     * @return employee dto
     */
    @Override
    public EmployeeDTO findByUsername(String username) {
        Employee employee = employeeService.findByUsername(username);
        return (employee == null) ? null : objectMapper.mapTo(employee, EmployeeDTO.class);
    }

    /**
     * Find all employees
     *
     * @return list of employees' dtos
     */
    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> list = employeeService.findAll();
        return objectMapper.mapTo(list, EmployeeDTO.class);
    }

    /**
     * Create new employee
     *
     * @param employee dto
     */
    @Override
    public EmployeeDTO create(EmployeeDTO employee) {
        Employee employeeEntity = objectMapper.mapTo(employee, Employee.class);
        employeeService.save(employeeEntity);
        return objectMapper.mapTo(employeeEntity, EmployeeDTO.class);
    }

    /**
     * Update the specified employee
     *
     * @param employee dto
     */
    @Override
    public EmployeeDTO update(EmployeeDTO employee) {
        Employee employeeEntity = objectMapper.mapTo(employee, Employee.class);
        employeeService.update(employeeEntity);
        return objectMapper.mapTo(employeeEntity, EmployeeDTO.class);
    }

    /**
     * Delete the reservation with the specified id
     *
     * @param id unambiguous identification of entity
     */
    @Override
    public void delete(Long id) {
        employeeService.delete(id);
    }
}
