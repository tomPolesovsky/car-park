package cz.pa165.carpark.persistence.entity;

import cz.pa165.carpark.persistence.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Employee of the company
 *
 * @author Tomáš Polešovský, 487574@mail.muni.cz
 */
@BatchSize(size = 100)
@Getter
@Setter
@Entity
@Table(name = "cp_employee")
public class Employee extends BaseEntity {

    /**
     * First name of person
     */
    private String firstName;

    /**
     * Last name of person
     */
    private String lastName;

    /**
     * Position in company
     */
    private String position;

    /**
     * Person's email
     */
    private String email;

    /**
     * Username for authentication
     */
    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Password for authentication
     */
    private String password;

    /**
     * Role in system
     */
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getUsername(), employee.getUsername());
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

}
