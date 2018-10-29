package cz.pa165.carpark.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The reservation entity. The reservation has properties from and to which represent date and time when will the
 * reservation start/end. There is a linkage to the employee which wants to make the reservation and also to the
 * vehicle (car) he wants to make the reservation for. Each reservation has its status.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@BatchSize(size = 100)
@Getter
@Setter
@Entity
@Table(name = "cp_reservation")
public class Reservation {

    /**
     * The identifier
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * The date and time from which will the reservation start
     */
    @NotNull
    @Column(name = "reservation_from", nullable = false)
    private LocalDateTime from;

    /**
     * The date and time from which will the reservation end
     */
    @NotNull
    @Column(name = "reservation_to", nullable = false)
    private LocalDateTime to;

    /**
     * The employee which is making the reservation
     */
    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private Employee employee;

    /**
     * The vehicle (car) which will be reserved
     */
    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private Vehicle vehicle;

    /**
     * The status of the reservation - new, approved, returned or canceled
     */
    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.NEW;

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(getFrom(), that.getFrom()) &&
                Objects.equals(getTo(), that.getTo()) &&
                Objects.equals(getEmployee(), that.getEmployee()) &&
                Objects.equals(getVehicle(), that.getVehicle());
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo(), getEmployee(), getVehicle());
    }

}
