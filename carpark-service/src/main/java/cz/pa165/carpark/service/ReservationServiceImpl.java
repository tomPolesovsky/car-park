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
import java.util.List;

/**
 * The reservation service's implementation.
 *
 * @author Jana Applova, 422352@mail.muni.cz
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
     * @param reservationSettings
     * @return true if the request was successfully processed else false
     */
    @Override
    public boolean processRequest(Reservation reservation, ReservationSettings reservationSettings) {
        List<Vehicle> allCars = vehicleDao.findAll();
        Vehicle chosenCar = reservation.getVehicle();
        if (allCars == null || !allCars.contains(chosenCar)){
            throw new UnavailableCarException("Our company does not rent this car.");
        }
        List<Reservation> reservations = reservationDao.findByVehicle(chosenCar);
        if (reservations.stream().anyMatch(r -> r.getTo().compareTo(reservation.getFrom()) > 0 &&
                                                r.getFrom().compareTo(reservation.getTo()) < 0)) {
            throw new UnavailableCarException("This car is not available in the selected time period.");
        }
        if (reservationSettings.getAutoApproval()){
            if (reservationSettings.getAllowed()){
                reservation.setStatus(ReservationStatus.APPROVED);
            }
            else {
                reservation.setStatus(ReservationStatus.CANCELED);
            }
        }
        else {
            reservation.setStatus(ReservationStatus.NEW);
        }
        reservationDao.save(reservation);
        return true;
    }

    /**
     * Accepts or declines the reservation request
     *
     * @param reservation dto
     * @param toBeAccepted
     */
    @Override
    public void acceptOrDecline(Reservation reservation, boolean toBeAccepted) {
        if (toBeAccepted){
            reservation.setStatus(ReservationStatus.APPROVED);
        }
        else {
            reservation.setStatus(ReservationStatus.CANCELED);
        }
        update(reservation);
    }

    /**
     * Occurs when car is returned
     *
     * @param reservation
     */
    @Override
    public void returned(Reservation reservation) {
        reservation.setStatus(ReservationStatus.RETURNED);
        update(reservation);
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
}
