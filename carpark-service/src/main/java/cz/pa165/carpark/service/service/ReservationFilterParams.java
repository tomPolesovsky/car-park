package cz.pa165.carpark.service.service;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The reservation filter parameters.
 *
 * @author Ondrej Svoren, 487558@mail.muni.cz
 */
@Getter
@Setter
public class ReservationFilterParams {

    /**
     * The page
     */
    private Integer page = 1;

    /**
     * The size of the page
     */
    private Integer pageSize = 10;

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
