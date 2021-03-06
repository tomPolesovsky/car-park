package cz.pa165.carpark.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * The vehicle data transfer object.
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
@Getter
@Setter
public class VehicleDTO {

    /**
     * The Identifier
     */
    private Long id;

    /**
     * The brand of the vehicle
     */
    private String brand;

    /**
     * The registration number of the vehicle
     */
    @NotNull
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
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleDTO)) return false;
        VehicleDTO vehicle = (VehicleDTO) o;
        return Objects.equals(getRegistrationNumber(), vehicle.getRegistrationNumber());
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(getRegistrationNumber());
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                '}';
    }

}
