package cz.pa165.carpark.service;

import cz.pa165.carpark.dao.ReservationDao;
import cz.pa165.carpark.dto.ReservationParamsDTO;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import cz.pa165.carpark.entity.Vehicle;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The reservation service's implementation.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationDao reservationDao;

    @Inject
    public ReservationServiceImpl(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
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
     * Save the specified reservation
     *
     * @param reservation
     */
    @Override
    public void save(Reservation reservation) {
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

    /**
     * Filter all the reservations according to the input params
     *
     * @param reservationFilterParams
     * @return list of reservations
     */
    @Override
    public List<Reservation> filter(ReservationFilterParams reservationFilterParams) {
        List<Reservation> reservations = findAll();
        List<Reservation> reservationResultList = new ArrayList<Reservation>();
        for (Reservation reservation : reservations) {
            if (reservation.getEmployee().getFirstName().equals(reservationFilterParams.getQuery()) ||
                reservation.getEmployee().getLastName().equals(reservationFilterParams.getQuery()) ||
                reservation.getEmployee().getUsername().equals(reservationFilterParams.getQuery()) ||
                reservation.getVehicle().getBrand().equals(reservationFilterParams.getQuery()) ||
                reservation.getVehicle().getRegistrationNumber().equals(reservationFilterParams.getQuery()) ||
                reservation.getVehicle().getType().equals(reservationFilterParams.getQuery()) &&
                        (reservation.getFrom().isEqual(reservationFilterParams.getFrom()) &&
                                reservation.getTo().isEqual(reservation.getTo()))) {
                reservationResultList.add(reservation);
            }
        }

        Long numberOfPages = reservationResultList.size() / reservationFilterParams.getPageSize();
        if (reservationFilterParams.getPage() <= numberOfPages) {
            Long firstIndex = (reservationFilterParams.getPage() - 1) * reservationFilterParams.getPageSize();
            reservationResultList.subList(Math.toIntExact(firstIndex), reservationResultList.size()).clear();
        }

        return reservationResultList;
    };
}
