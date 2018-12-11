package cz.pa165.carpark.service.facade;

import cz.pa165.carpark.api.dto.EmployeeDTO;
import cz.pa165.carpark.api.dto.ReservationSettingsDTO;
import cz.pa165.carpark.api.facade.ReservationSettingsFacade;
import cz.pa165.carpark.persistence.entity.Employee;
import cz.pa165.carpark.persistence.entity.ReservationSettings;
import cz.pa165.carpark.service.service.ReservationSettingsService;
import cz.pa165.carpark.service.util.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * The reservation facade's implementation.
 *
 * @author Ondrej Svoren, 487558@mail.muni.cz
 */
@Transactional
@Service
public class ReservationSettingsFacadeImpl implements ReservationSettingsFacade {

    private ObjectMapper objectMapper;

    private ReservationSettingsService reservationSettingsService;

    @Inject
    public ReservationSettingsFacadeImpl(ObjectMapper objectMapper, ReservationSettingsService reservationSettingsService) {
        this.objectMapper = objectMapper;
        this.reservationSettingsService = reservationSettingsService;
    }

    /**
     * Find the reservation settings with the specified id
     *
     * @param id unambiguous identification of entity
     * @return reservation dto
     */
    @Override
    public ReservationSettingsDTO find(Long id) {
        ReservationSettings reservationSettings = reservationSettingsService.find(id);
        return (reservationSettings == null) ? null : objectMapper.mapTo(reservationSettings, ReservationSettingsDTO.class);
    }


    /**
     * Find all the reservation settings for the specified employee
     *
     * @param employee
     * @return list of reservation settings
     */
    @Override
    public ReservationSettingsDTO findByEmployee(EmployeeDTO employee) {
        Employee employeeEntity = objectMapper.mapTo(employee, Employee.class);
        ReservationSettings list = reservationSettingsService.findByEmployee(employeeEntity);
        return objectMapper.mapTo(list, ReservationSettingsDTO.class);
    }

    /**
     * Find all reservation settings
     *
     * @return list of reservation' dtos
     */
    @Override
    public List<ReservationSettingsDTO> findAll() {
        List<ReservationSettings> list = reservationSettingsService.findAll();
        return objectMapper.mapTo(list, ReservationSettingsDTO.class);
    }

    /**
     * Create new reservation
     *
     * @param reservationSettings dto
     */
    @Override
    public ReservationSettingsDTO create(ReservationSettingsDTO reservationSettings) {
        ReservationSettings reservationSettingsEntity = objectMapper.mapTo(reservationSettings, ReservationSettings.class);
        reservationSettingsService.save(reservationSettingsEntity);
        return objectMapper.mapTo(reservationSettingsEntity, ReservationSettingsDTO.class);
    }

    /**
     * Update the specified reservation
     *
     * @param reservationSettings dto
     */
    @Override
    public ReservationSettingsDTO update(ReservationSettingsDTO reservationSettings) {
        ReservationSettings reservationSettingsEntity = objectMapper.mapTo(reservationSettings, ReservationSettings.class);
        reservationSettingsService.update(reservationSettingsEntity);
        return objectMapper.mapTo(reservationSettingsEntity, ReservationSettingsDTO.class);
    }

    /**
     * Delete the reservation with the specified id
     *
     * @param id unambiguous identification of entity
     */
    @Override
    public void delete(Long id) {
        reservationSettingsService.delete(id);
    }
}
