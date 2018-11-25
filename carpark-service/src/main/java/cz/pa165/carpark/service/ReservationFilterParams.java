package cz.pa165.carpark.service;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The reservation parameters data transfer object.
 *
 * @author Ondrej Svoren, 487558@mail.muni.cz
 */
@Getter
@Setter
public class ReservationFilterParams {
    /**
     * The page
     */
    private Long page;

    /**
     * The size of the page
     */
    private Long pageSize;

    /**
     *  The query string
     */
    private String query;

    /**
     * Filter reservations from this date
     */
    private LocalDateTime from;

    /**
     * Filter reservations to this date
     */
    private LocalDateTime to;
}
