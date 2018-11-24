package cz.pa165.carpark.service;

/**
 * The reservation parameters data transfer object.
 *
 * @author Ondrej Svoren, 487558@mail.muni.cz
 */
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
}
