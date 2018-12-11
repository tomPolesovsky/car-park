package cz.pa165.carpark.service.service;

import cz.pa165.carpark.persistence.dao.VehicleDao;
import cz.pa165.carpark.persistence.entity.Vehicle;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleDao vehicleDao;

    @Inject
    public VehicleServiceImpl(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @Override
    public Vehicle find(Long id) {
        return vehicleDao.find(id);
    }

    @Override
    public Vehicle findByRegistrationNumber(String registrationNumber) {
        return vehicleDao.findByRegistrationNumber(registrationNumber);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleDao.findAll();
    }

    @Override
    public void save(Vehicle vehicle) {
        vehicleDao.save(vehicle);
    }

    @Override
    public void update(Vehicle vehicle) {
        vehicleDao.update(vehicle);
    }

    @Override
    public void delete(Long id) {
        vehicleDao.delete(id);
    }

}
