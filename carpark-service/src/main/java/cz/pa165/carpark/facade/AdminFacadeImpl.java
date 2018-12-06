package cz.pa165.carpark.facade;

import cz.pa165.carpark.dto.UserDTO;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.service.AdminService;
import cz.pa165.carpark.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * The admin facade's implementation.
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
@Transactional
@Service
public class AdminFacadeImpl implements AdminFacade {

    private AdminService adminService;

    @Inject
    public AdminFacadeImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public UserDTO login(String username, String password) {
        Employee employee = adminService.login(username, password);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmployeeId(employee.getId());
        userDTO.setFirstName(employee.getFirstName());
        userDTO.setLastName(employee.getLastName());
        userDTO.setRole(employee.getRole());
        return userDTO;
    }

}
