package cz.pa165.carpark.persistence.dao;

import cz.pa165.carpark.persistence.entity.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

/**
 * Specific implementation of {@link VehicleDao} interface
 *
 * @author Ondrej Svoreň
 */
@Repository
public class VehicleDaoImpl extends DaoImpl<Vehicle> implements VehicleDao {

    public VehicleDaoImpl() {
        super(Vehicle.class);
    }

    /**
     * Find specific vehicle by its registration number
     *
     * @param registrationNumber registration number of vehicle
     * @return entity object
     */
    @Override
    public Vehicle findByRegistrationNumber(String registrationNumber) {
        return em.createQuery("from Vehicle where registrationNumber = :registrationNumber", Vehicle.class)
                .setParameter("registrationNumber", registrationNumber)
                .getSingleResult();
    }

}
