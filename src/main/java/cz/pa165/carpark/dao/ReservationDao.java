package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import cz.pa165.carpark.entity.Vehicle;

import java.util.List;

/**
 * Interface for Reservation' DAO
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
public interface ReservationDao extends Dao<Reservation>  {

    /**
     * Find all the reservations for the specified employee
     *
     * @param employee
     * @return list of reservations
     */
    List<Reservation> findByEmployee(Employee employee);

    /**
     * Find all the reservations for the specified vehicle
     *
     * @param vehicle
     * @return list of reservations
     */
    List<Reservation> findByVehicle(Vehicle vehicle);

}
