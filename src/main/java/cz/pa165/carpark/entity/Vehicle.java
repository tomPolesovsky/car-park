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

    /**
     * The identifier
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * The brand of the vehicle
     */
    private String brand;

    /**
     * The registration number of the vehicle
     */
    @NotNull
    @Column(nullable = false, unique = true)
    private String registrationNumber;

    /**
     * The type of the vehicle
     */
    private String type;

    /**
     * The color of the vehicle
     */
    private String color;

    /**
     * The mileage of the vehicle
     */
    private Long mileage;

    /**
     * The overriden equals method.
     * @param o of Vehicle type
     * @return true if the vehicles are the same else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(getRegistrationNumber(), vehicle.getRegistrationNumber());
    }

    /**
     * The overriden hashCode method.
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(getRegistrationNumber());
    }
}
