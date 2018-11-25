package cz.pa165.carpark.facade;

import cz.pa165.carpark.dto.EmployeeDTO;
import cz.pa165.carpark.dto.ReservationDTO;
import cz.pa165.carpark.dto.VehicleDTO;
import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.entity.Reservation;
import cz.pa165.carpark.entity.ReservationSettings;
import cz.pa165.carpark.entity.Vehicle;
import cz.pa165.carpark.service.MailingService;
import cz.pa165.carpark.service.ReservationService;
import cz.pa165.carpark.service.ReservationSettingsService;
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

    private ReservationSettingsService reservationSettingsService;

    private MailingService mailingService;

    @Inject
    public ReservationFacadeImpl(ObjectMapper objectMapper,
                                 ReservationService reservationService,
                                 MailingService mailingService) {
        this.objectMapper = objectMapper;
        this.reservationService = reservationService;
        this.mailingService = mailingService;
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
     * Processes the reservation request
     *
     * @param reservation dto
     */
    @Override
    public void processRequest(ReservationDTO reservation) {
        Reservation reservationEntity = objectMapper.mapTo(reservation, Reservation.class);
        ReservationSettings reservationSettings =
                reservationSettingsService.findByEmployee(reservationEntity.getEmployee());
        boolean resultOk = reservationService.processRequest(reservationEntity, reservationSettings);
        if (reservationSettings.getAutoApproval()) {
            if (resultOk){
                mailingService.SendConfirmation(reservationEntity);
            }
            else {
                mailingService.SendDeclination(reservationEntity);
            }
        }
        else {
            mailingService.SendRequestForApproval(reservationEntity);
        }
    }

    /**
     * Accepts or declines the reservation request
     *
     * @param reservation dto
     * @param toBeAccepted
     */
    @Override
    public void acceptOrDecline(ReservationDTO reservation, boolean toBeAccepted) {
        Reservation reservationEntity = objectMapper.mapTo(reservation, Reservation.class);
        reservationService.acceptOrDecline(reservationEntity, toBeAccepted);
        if (toBeAccepted) {
            mailingService.SendConfirmation(reservationEntity);
        }
        else {
            mailingService.SendDeclination(reservationEntity);
        }
    }

    /**
     * Occurs when car is returned
     *
     * @param reservation dto
     */
    @Override
    public void returned(ReservationDTO reservation) {
        Reservation reservationEntity = objectMapper.mapTo(reservation, Reservation.class);
        reservationService.returned(reservationEntity);
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
