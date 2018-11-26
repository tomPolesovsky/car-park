package cz.pa165.carpark.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * The reservation settings data transfer object.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@Getter
@Setter
public class ReservationSettingsDTO {

    /**
     * The identifier
     */
    private Long id;

    /**
     * Reservation settings for this employee
     */
    @NotNull
    private EmployeeDTO employee;

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
        if (!(o instanceof ReservationSettingsDTO)) return false;
        ReservationSettingsDTO that = (ReservationSettingsDTO) o;
        return Objects.equals(getEmployee(), that.getEmployee());
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(getEmployee());
    }

    @Override
    public String toString() {
        return "ReservationSettingsDTO{" +
                "id=" + id +
                ", employee='" + employee+ '\'' +
                ", allowed='" + allowed+ '\'' +
                ", autoApproval='" + autoApproval + '\'' +
                '}';
    }

}
