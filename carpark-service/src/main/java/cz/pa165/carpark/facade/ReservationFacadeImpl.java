package cz.pa165.carpark.facade;

import cz.pa165.carpark.dto.EmployeeDTO;
import cz.pa165.carpark.dto.ReservationDTO;
import cz.pa165.carpark.dto.VehicleDTO;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import cz.pa165.carpark.entity.Vehicle;
import cz.pa165.carpark.service.ReservationService;
import cz.pa165.carpark.util.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * The reservation facade's implementation.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
@Transactional
@Service
public class ReservationFacadeImpl implements ReservationFacade {

    private ObjectMapper objectMapper;

    private ReservationService reservationService;

    @Inject
    public ReservationFacadeImpl(ObjectMapper objectMapper, ReservationService reservationService) {
        this.objectMapper = objectMapper;
        this.reservationService = reservationService;
    }

    /**
     * Find the reservation with the specified id
     *
     * @param id unambiguous identification of entity
     * @return reservation dto
     */
    @Override
    public ReservationDTO find(Long id) {
        Reservation reservation = reservationService.find(id);
        return (reservation == null) ? null : objectMapper.mapTo(reservation, ReservationDTO.class);
    }

    /**
     * Find all the reservations for the specified employee
     *
     * @param employee
     * @return list of reservations
     */
    @Override
    public List<ReservationDTO> findByEmployee(EmployeeDTO employee) {
        Employee employeeEntity = objectMapper.mapTo(employee, Employee.class);
        List<Reservation> list = reservationService.findByEmployee(employeeEntity);
        return objectMapper.mapTo(list, ReservationDTO.class);
    }

    /**
     * Find all the reservations for the specified vehicle
     *
     * @param vehicle
     * @return list of reservations
     */
    @Override
    public List<ReservationDTO> findByVehicle(VehicleDTO vehicle) {
        Vehicle vehicleEntity = objectMapper.mapTo(vehicle, Vehicle.class);
        List<Reservation> list = reservationService.findByVehicle(vehicleEntity);
        return objectMapper.mapTo(list, ReservationDTO.class);
    }

    /**
     * Find all reservations
     *
     * @return list of reservation' dtos
     */
    @Override
    public List<ReservationDTO> findAll() {
        List<Reservation> list = reservationService.findAll();
        return objectMapper.mapTo(list, ReservationDTO.class);
    }

    /**
     * Create new reservation
     *
     * @param reservation dto
     */
    @Override
    public ReservationDTO create(ReservationDTO reservation) {
        Reservation reservationEntity = objectMapper.mapTo(reservation, Reservation.class);
        reservationService.save(reservationEntity);
        return objectMapper.mapTo(reservationEntity, ReservationDTO.class);
    }

    /**
     * Update the specified reservation
     *
     * @param reservation dto
     */
    @Override
    public ReservationDTO update(ReservationDTO reservation) {
        Reservation reservationEntity = objectMapper.mapTo(reservation, Reservation.class);
        reservationService.update(reservationEntity);
        return objectMapper.mapTo(reservationEntity, ReservationDTO.class);
    }

    /**
     * Delete the reservation with the specified id
     *
     * @param id unambiguous identification of entity
     */
    @Override
    public void delete(Long id) {
        reservationService.delete(id);
    }
}
