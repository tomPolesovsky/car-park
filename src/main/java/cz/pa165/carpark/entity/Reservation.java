package cz.pa165.carpark.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
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
    private LocalDateTime from;

    /**
     * The date and time from which will the reservation end
     */
    private LocalDateTime to;

    /**
     * The employee which wants is making the reservation
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    /**
     * The vehicle (car) which will be reserved
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    /**
     * The status of the reservation - new, approved, returned or canceled
     */
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    /**
     * The overriden equals method.
     * @param o of Reservation type
     * @return true if the reservations are the same else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation r = (Reservation) o;
        return Objects.equals(getId(), r.getId());
    }

    /**
     * The overriden hashCode method.
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}