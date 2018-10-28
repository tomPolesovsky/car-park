package cz.pa165.carpark.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Objects;

/**
 * The reservation settings. For each employee there is a setting which specifies how will be the reservation handled.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@BatchSize(size = 100)
@Getter
@Setter
@Entity
@Table(name = "cp_reservation_settings")
public class ReservationSettings {

    /**
     * The identifier
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Reservation settings for this employee
     */
    @OneToOne
    private Employee employee;

    /**
     * If true the employee is on the whitelist - the employee can reserve a vehicle (car)
     * otherwise is on the blacklist - his reservations will not be approved
     */
    private Boolean allowed;

    /**
     * If true the automatic approval is allowed
     * otherwise the approver has to approve it manually
     */
    private Boolean autoApproval;

    /**
     * The overriden equals method.
     * @param o of ReservationSettings type
     * @return true if the reservationSettings are the same else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationSettings)) return false;
        ReservationSettings that = (ReservationSettings) o;
        return Objects.equals(getEmployee(), that.getEmployee());
    }

    /**
     * The overriden hashCode method.
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(getEmployee());
    }
}
