package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation for Reservation' DAO.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@Transactional
@Repository
public class ReservationDaoImpl extends DaoImpl<Reservation> implements ReservationDao  {

    /**
     * Initializes a new instance of ReservationDaoImpl class.
     */
    public ReservationDaoImpl() {
        super(Reservation.class);
    }

    @Override
    public List<Reservation> findByEmployee(Employee employee) {
        return null;
    }

    @Override
    public List<Reservation> findByVehicle(Vehicle vehicle) {
        return null;
    }
}