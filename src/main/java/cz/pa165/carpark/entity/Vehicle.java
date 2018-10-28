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
@Table(name = "cp_vehicle")

public class Vehicle {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String brand;

    @NotNull
    @Column(nullable = false, unique = true)
    private String registrationNumber;

    private String type;

    private String color;

    private Long mileage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(getRegistrationNumber(), vehicle.getRegistrationNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistrationNumber());
    }
}
