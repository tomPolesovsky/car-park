package cz.pa165.carpark.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The reservation data transfer object.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@Getter
@Setter
public class ReservationDTO {
    /**
     * The identifier
     */
    private Long id;

    /**
     * The date and time from which will the reservation start
     */
    private LocalDateTime from;

    /**
     * The date and time from which will the reservation end
     */
    private LocalDateTime to;

    /**
     * The employee which is making the reservation
     */
    private EmployeeDTO employee;

    /**
     * The vehicle (car) which will be reserved
     */
    private VehicleDTO vehicle;

    /**
     * The status of the reservation - new, approved, returned or canceled
     */
    private ReservationStatus status = ReservationStatus.NEW;

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationDTO)) return false;
        ReservationDTO that = (ReservationDTO) o;
        return Objects.equals(getFrom(), that.getFrom()) &&
                Objects.equals(getTo(), that.getTo()) &&
                Objects.equals(getEmployee(), that.getEmployee());
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo(), getEmployee());
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", employee='" + employee + '\'' +
                ", vehicle='" + vehicle + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
