package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Vehicle;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

@Transactional
@Repository
public class VehicleDaoImpl extends DaoImpl<Vehicle> implements VehicleDao {

    public VehicleDaoImpl() {
        super(Vehicle.class
        );
    }

    @Override
    public Vehicle findByRegistrationNumber(String registrationNumber) {
        try {
            return em.createQuery("from Vehicle where registrationNumber = :registrationNumber", Vehicle.class)
                    .setParameter("registrationNumber", registrationNumber)
                    .getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }
}
