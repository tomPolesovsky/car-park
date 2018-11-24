package cz.pa165.carpark.service;

import cz.pa165.carpark.dao.ReservationDao;
import cz.pa165.carpark.dao.VehicleDao;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import cz.pa165.carpark.entity.ReservationSettings;
import cz.pa165.carpark.entity.Vehicle;
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
     */
    @Override
    public boolean processRequest(Reservation reservation) {
        List<Vehicle> allCars = vehicleDao.findAll();
        if (allCars == null || !allCars.contains(reservation.getVehicle())){
            throw new UnavailableCarException("Our company does not rent this car.");
        }
        Vehicle chosenCar = reservation.getVehicle();
        List<Reservation> reservations = reservationDao.findByVehicle(chosenCar);

        //to be continued

        save(reservation);
        return true;
    }

    /**
     * Processes reservation request manually
     *
     * @param reservation
     */
    @Override
    public boolean processRequestManually(Reservation reservation, ReservationSettings reservationSettings) {
        //to be continued

        save(reservation);
        return true;
    }

    private void save(Reservation reservation) {
        reservationDao.save(reservation);
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
