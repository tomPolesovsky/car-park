package cz.pa165.carpark.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginDTO {

    @NotNull
    private String username;

    private String password;
}
