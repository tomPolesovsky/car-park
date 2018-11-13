package cz.pa165.carpark.entity;

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
public class Employee {

    /**
     * The identifier
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Password for authentication
     */
    @Transient
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
