package cz.pa165.carpark.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * The employee data transfer object.
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
@Getter
@Setter
public class EmployeeDTO {

    /**
     * The identifier
     */
    private Long id;

    /**
     * First name of person
     */
    private String firstName;

    /**
     * Last name of person
     */
    private String lastName;

    /**
     * Username for authentication
     */
    @NotNull
    private String username;

    /**
     * Person's email
     */
    private String email;

    /**
     * Password for authentication
     */
    private String password;

    /**
     * Position in company
     */
    private String position;

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDTO)) return false;
        EmployeeDTO employee = (EmployeeDTO) o;
        return Objects.equals(getUsername(), employee.getUsername());
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

}
