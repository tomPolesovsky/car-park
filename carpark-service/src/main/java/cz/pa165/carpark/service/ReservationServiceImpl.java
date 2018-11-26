package cz.pa165.carpark.service;

import cz.pa165.carpark.dao.ReservationDao;
import cz.pa165.carpark.dao.VehicleDao;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import cz.pa165.carpark.entity.ReservationSettings;
import cz.pa165.carpark.entity.Vehicle;
import cz.pa165.carpark.enums.ReservationStatus;
import cz.pa165.carpark.exception.UnavailableCarException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The reservation service's implementation.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 * @author Ondrej Svoren, 487558@mail.muni.cz
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationDao reservationDao;

    private VehicleDao vehicleDao;

    @Inject
    public ReservationServiceImpl(ReservationDao reservationDao, VehicleDao vehicleDao) {
        this.reservationDao = reservationDao;
        this.vehicleDao = vehicleDao;
    }

    /**
     * Find the reservation with the specified id
     *
     * @param id unambiguous identification of entity
     * @return reservation
     */
    @Override
    public Reservation find(Long id) {
        return reservationDao.find(id);
    }

    /**
     * Find all the reservations for the specified employee
     *
     * @param employee
     * @return list of reservations
     */
    @Override
    public List<Reservation> findByEmployee(Employee employee) {
        return reservationDao.findByEmployee(employee);
    }

    /**
     * Find all the reservations for the specified vehicle
     *
     * @param vehicle
     * @return list of reservations
     */
    @Override
    public List<Reservation> findByVehicle(Vehicle vehicle) {
        return reservationDao.findByVehicle(vehicle);
    }

    /**
     * Find all reservations
     *
     * @return list of all reservations
     */
    @Override
    public List<Reservation> findAll() {
        return reservationDao.findAll();
    }

    /**
     * Processes reservation request
     *
     * @param reservation
     * @param settings
     * @return true if the request was successfully processed else false
     */
    @Override
    public void processRequest(Reservation reservation, ReservationSettings settings) {
        if (!reservationDao.isVehicleAvailable(reservation.getVehicle(), reservation.getFrom(), reservation.getTo())) {
            throw new UnavailableCarException("This car does not exists or it isn't available in the selected time period.");
        }

        if (settings.getAutoApproval() && settings.getAllowed()) {
            reservation.setStatus(ReservationStatus.APPROVED);
        }else if (!settings.getAutoApproval() && settings.getAllowed()){
            reservation.setStatus(ReservationStatus.NEW);
        }else{
            reservation.setStatus(ReservationStatus.CANCELED);
        }

        reservationDao.save(reservation);
    }

    /**
     * Accepts or declines the reservation request
     *
     * @param reservation dto
     * @param toBeAccepted
     */
    @Override
    public void acceptOrDecline(Reservation reservation, boolean toBeAccepted) {
        if (!toBeAccepted){
            reservation.setStatus(ReservationStatus.CANCELED);
        }else{
            reservation.setStatus(ReservationStatus.APPROVED);
        }

        reservationDao.update(reservation);
    }

    /**
     * Update the specified reservation
     *
     * @param reservation
     */
    @Override
    public void update(Reservation reservation) { reservationDao.update(reservation); }

    /**
     * Delete the reservation with the specified id
     *
     * @param id unambiguous identification of entity
     */
    @Override
    public void delete(Long id) {
        reservationDao.delete(id);
    }

    /**
     * Filter all the reservations according to the input params
     *
     * @param params
     * @return list of reservations
     */
    @Override
    public List<Reservation> filter(ReservationFilterParams params) {
        List<Reservation> reservationList = this.findAll();
        Set<Reservation> queryResultSet = new HashSet<>();
        String query = params.getQuery();

        for (Reservation reservation : reservationList) {
            Employee employee = reservation.getEmployee();
            if (this.contains(employee.getFirstName(), query)
                    || this.contains(employee.getLastName(), (query))
                    || this.contains(employee.getUsername(), query)
                    || this.contains(employee.getEmail(), query)
                    || this.contains(employee.getPosition(), query)) {
                queryResultSet.add(reservation);
            }

            Vehicle vehicle = reservation.getVehicle();
            if (this.contains(vehicle.getRegistrationNumber(), query)
                    || this.contains(vehicle.getBrand(), query)
                    || this.contains(vehicle.getColor(), query)
                    || this.contains(vehicle.getType(), query)) {
                queryResultSet.add(reservation);
            }
        }

        List<Reservation> filteredList = this.filterInterval(queryResultSet, params.getFrom(), params.getTo());
        return this.paginate(filteredList, params.getPage(), params.getPageSize());
    }

    /**
     *
     * @param reservations list of reservations
     * @param from date time from
     * @param to date time to
     * @return filtered list
     */
    private List<Reservation> filterInterval(Set<Reservation> reservations, LocalDateTime from, LocalDateTime to) {
        if (from == null || to == null) {
            return new ArrayList<>(reservations);
        }

        return reservations.stream().filter(
            item -> (item.getFrom().isAfter(from) && item.getTo().isBefore(to))
        ).collect(Collectors.toList());
    }

    /**
     * Return true if field string contains query string - case insensitive
     *
     * @param field field string
     * @param query query string
     * @return true or false
     */
    private boolean contains(String field, String query) {
        return field != null && query != null && field.toLowerCase().contains(query.toLowerCase());
    }

    /**
     * Creates sublist of reservations for pagination
     *
     * @param reservations list of reservations
     * @param page
     * @param pageSize
     * @return paginated list
     */
    private List<Reservation> paginate(List<Reservation> reservations, Integer page, Integer pageSize) {
        if (page == null || pageSize == null || page <= 0 || pageSize <= 0) {
            return reservations;
        }

        int from = (page - 1) * pageSize;
        if (reservations.size() < from) {
            return new ArrayList<>();
        }

        return reservations.subList(from, Math.min(from + pageSize, reservations.size()));
    }

}
