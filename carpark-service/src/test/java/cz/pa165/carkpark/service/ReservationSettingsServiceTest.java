package cz.pa165.carkpark.service;

import cz.pa165.carkpark.util.AbstractJUnitTest;
import cz.pa165.carpark.dao.ReservationSettingsDao;
import cz.pa165.carpark.service.ReservationSettingsServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * The tests for reservation settings service.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
public class ReservationSettingsServiceTest extends AbstractJUnitTest {

    @Mock
    private ReservationSettingsDao reservationSettingsDao;

    @InjectMocks
    private ReservationSettingsServiceImpl reservationSettingsService;
}
