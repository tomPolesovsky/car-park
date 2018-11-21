package cz.pa165.carpark.facade;

import cz.pa165.carpark.dto.VehicleDTO;
import cz.pa165.carpark.entity.Vehicle;
import cz.pa165.carpark.service.VehicleService;
import cz.pa165.carpark.util.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Transactional
@Service
public class VehicleFacadeImpl implements VehicleFacade {

    private ObjectMapper objectMapper;

    private VehicleService vehicleService;

    @Inject
    public VehicleFacadeImpl(ObjectMapper objectMapper, VehicleService vehicleService) {
        this.objectMapper = objectMapper;
        this.vehicleService = vehicleService;
    }

    @Override
    public VehicleDTO find(Long id) {
        Vehicle vehicle = vehicleService.find(id);
        return (vehicle == null) ? null : objectMapper.mapTo(vehicle, VehicleDTO.class);
    }

    @Override
    public VehicleDTO findByRegistrationNumber(String registrationNumber) {
        Vehicle vehicle = vehicleService.findByRegistrationNumber(registrationNumber);
        return (vehicle == null) ? null : objectMapper.mapTo(vehicle, VehicleDTO.class);
    }

    @Override
    public List<VehicleDTO> findAll() {
        List<Vehicle> list = vehicleService.findAll();
        return objectMapper.mapTo(list, VehicleDTO.class);
    }

    @Override
    public VehicleDTO create(VehicleDTO vehicle) {
        Vehicle vehicleEntity = objectMapper.mapTo(vehicle, Vehicle.class);
        vehicleService.save(vehicleEntity);
        return objectMapper.mapTo(vehicleEntity, VehicleDTO.class);
    }

    @Override
    public VehicleDTO update(VehicleDTO vehicle) {
        Vehicle vehicleEntity = objectMapper.mapTo(vehicle, Vehicle.class);
        vehicleService.save(vehicleEntity);
        return objectMapper.mapTo(vehicleEntity, VehicleDTO.class);
    }

    @Override
    public void delete(Long id) {
        vehicleService.delete(id);
    }

}
