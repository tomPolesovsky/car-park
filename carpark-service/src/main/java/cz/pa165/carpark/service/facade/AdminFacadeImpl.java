package cz.pa165.carpark.service.facade;

import cz.pa165.carpark.api.dto.UserDTO;
import cz.pa165.carpark.api.enums.UserRole;
import cz.pa165.carpark.api.facade.AdminFacade;
import cz.pa165.carpark.persistence.entity.Employee;
import cz.pa165.carpark.service.service.AdminService;
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
        if (employee.getRole() != null) {
            userDTO.setRole(UserRole.valueOf(employee.getRole().name()));
        }
        return userDTO;
    }

}
