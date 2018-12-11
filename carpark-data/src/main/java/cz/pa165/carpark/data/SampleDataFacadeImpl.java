package cz.pa165.carpark.data;

import cz.pa165.carpark.persistence.entity.Employee;
import cz.pa165.carpark.persistence.enums.UserRole;
import cz.pa165.carpark.service.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Transactional
@Service
public class SampleDataFacadeImpl implements SampleDataFacade {

    private EmployeeService employeeService;

    @Inject
    public SampleDataFacadeImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void loadData() {
        Employee employee = new Employee();
        employee.setFirstName("Petr");
        employee.setLastName("Kelner");
        employee.setPosition("CEO");
        employee.setEmail("admin@company.cz");
        employee.setUsername("admin");
        employee.setPassword("admin");
        employee.setRole(UserRole.APPROVER);
        employeeService.save(employee);
    }

}
