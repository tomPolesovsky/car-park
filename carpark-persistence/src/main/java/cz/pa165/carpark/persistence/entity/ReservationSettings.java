package cz.pa165.carpark.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
public class ReservationSettings extends BaseEntity {

    /**
     * Reservation settings for this employee
     */
    @NotNull
    @JoinColumn(nullable = false, unique = true)
    @OneToOne
    private Employee employee;

    /**
     * If true the employee is on the whitelist - the employee can reserve a vehicle (car)
     * otherwise is on the blacklist - his reservations will not be approved
     */
    private Boolean allowed = true;

    /**
     * If true the automatic approval is allowed
     * otherwise the approver has to approve it manually
     */
    private Boolean autoApproval = false;

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationSettings)) return false;
        ReservationSettings reservationSettings = (ReservationSettings) o;
        return Objects.equals(getEmployee(), reservationSettings.getEmployee());
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(getEmployee());
    }

}
