package cz.pa165.carpark.controller;

import cz.pa165.carpark.config.ApiConfiguration;
import cz.pa165.carpark.dto.EmployeeDTO;
import cz.pa165.carpark.dto.UserDTO;
import cz.pa165.carpark.exception.AuthenticationException;
import cz.pa165.carpark.exception.LoginException;
import cz.pa165.carpark.facade.AdminFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping(ApiConfiguration.API_LOGIN)
public class LoginController {

    private AdminFacade adminFacade;

    @Inject
    public LoginController(AdminFacade adminFacade) {
        this.adminFacade = adminFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserDTO login(@RequestParam("username") String username,
                         @RequestParam("password") String password
    ) {
        try{
            return adminFacade.login(username, password);
        }catch (Exception e) {
            throw new LoginException();
        }
    }

}
