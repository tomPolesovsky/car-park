package cz.pa165.carpark.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@BatchSize(size = 100)
@Getter
@Setter
@Entity
@Table(name = "cp_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @Transient
    private String password;

    private String position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getUsername(), employee.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

}
