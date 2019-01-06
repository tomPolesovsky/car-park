package cz.pa165.carpark.persistence.dao;

import cz.pa165.carpark.persistence.entity.Employee;
import cz.pa165.carpark.persistence.entity.Reservation;
import cz.pa165.carpark.persistence.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation for Reservation' DAO.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@Repository
public class ReservationDaoImpl extends DaoImpl<Reservation> implements ReservationDao  {

    /**
     * Initializes a new instance of ReservationDaoImpl class.
     */
    public ReservationDaoImpl() {
        super(Reservation.class);
    }

    /**
     * Find all the reservations for the specified employee
     * @param employee
     * @return list of reservations
     */
    @Override
    public List<Reservation> findByEmployee(Employee employee) {
        return em.createQuery("select r from Reservation r where r.employee = :employee", Reservation.class)
                 .setParameter("employee", employee)
                 .getResultList();
    }

    /**
     * Find all the reservations for the specified vehicle
     * @param vehicle
     * @return list of reservations
     */
    @Override
    public List<Reservation> findByVehicle(Vehicle vehicle) {
        return em.createQuery("select r from Reservation r where r.vehicle = :vehicle", Reservation.class)
                 .setParameter("vehicle", vehicle)
                 .getResultList();
    }

    /**
     * Return true if vehicle is available in specific interval
     *
     * @param vehicle vehicle entity
     * @param from date time from
     * @param to date time to
     * @return true or false
     */
    @Override
    public boolean isVehicleAvailable(Vehicle vehicle, LocalDateTime from, LocalDateTime to) {
        List<Reservation> result = em.createQuery("select r from Reservation r where r.vehicle = :vehicle and (:dateFrom <= r.to and :dateTo >= r.from)", Reservation.class)
                .setParameter("vehicle", vehicle)
                .setParameter("dateFrom", from)
                .setParameter("dateTo", to)
                .getResultList();

        return result.size() <= 0;
    }

}
