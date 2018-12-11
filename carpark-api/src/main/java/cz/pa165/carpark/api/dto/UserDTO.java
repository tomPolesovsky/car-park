package cz.pa165.carpark.api.dto;

import cz.pa165.carpark.api.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserDTO {

    /**
     * The identifier
     */
    private Long employeeId;

    /**
     * First name of person
     */
    private String firstName;

    /**
     * Last name of person
     */
    private String lastName;

    /**
     * Role in system
     */
    private UserRole role;

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(employeeId, userDTO.employeeId);
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }

}
