package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Vehicle;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class VehicleDaoImpl extends DaoImpl<Vehicle> implements VehicleDao {

    public VehicleDaoImpl() {
        super(Vehicle.class);
    }

}
