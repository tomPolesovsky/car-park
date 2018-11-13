package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

/**
 * Specific implementation of {@link VehicleDao} interface
 *
 * @param <T> generic type annotated by {@link Entity}
 * @author Ondrej Svoreň
 */
@Repository
public class VehicleDaoImpl extends DaoImpl<Vehicle> implements VehicleDao {

    public VehicleDaoImpl() {
        super(Vehicle.class
        );
    }

    @Override
    public Vehicle findByRegistrationNumber(String registrationNumber) {
        return em.createQuery("from Vehicle where registrationNumber = :registrationNumber", Vehicle.class)
                .setParameter("registrationNumber", registrationNumber)
                .getSingleResult();
    }

}
