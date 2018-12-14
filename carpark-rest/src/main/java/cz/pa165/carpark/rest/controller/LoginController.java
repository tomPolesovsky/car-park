package cz.pa165.carpark.rest.controller;

import cz.pa165.carpark.api.dto.UserDTO;
import cz.pa165.carpark.api.facade.AdminFacade;
import cz.pa165.carpark.rest.config.ApiConfiguration;
import cz.pa165.carpark.rest.exception.LoginException;
import cz.pa165.carpark.rest.security.jwt.JwtTokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping(ApiConfiguration.API_LOGIN)
public class LoginController {

    private AdminFacade adminFacade;

    private JwtTokenProvider jwtTokenProvider;

    @Inject
    public LoginController(AdminFacade adminFacade, JwtTokenProvider jwtTokenProvider) {
        this.adminFacade = adminFacade;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> login(@RequestParam("username") String username,
                                         @RequestParam("password") String password
    ) {
        try {
            UserDTO userDTO = adminFacade.login(username, password);
            String token = jwtTokenProvider.createToken(username);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            return ResponseEntity.ok().headers(headers).body(userDTO);
        } catch (Exception e) {
            throw new LoginException();
        }
    }

}
