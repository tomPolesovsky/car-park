package cz.pa165.carpark.rest.controller;

import cz.pa165.carpark.api.dto.LoginDTO;
import cz.pa165.carpark.api.dto.UserDTO;
import cz.pa165.carpark.api.facade.AdminFacade;
import cz.pa165.carpark.rest.config.ApiConfiguration;
import cz.pa165.carpark.rest.exception.LoginException;
import cz.pa165.carpark.rest.security.jwt.JwtTokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

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
    public ResponseEntity<UserDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            UserDTO userDTO = adminFacade.login(loginDTO.getUsername(), loginDTO.getPassword());
            String token = jwtTokenProvider.createToken(loginDTO.getUsername());
            userDTO.setToken(token);

            return ResponseEntity.ok().body(userDTO);
        } catch (Exception e) {
            throw new LoginException();
        }
    }

}
