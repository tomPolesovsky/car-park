package cz.pa165.carkpark.facade;

import cz.pa165.carkpark.util.AbstractJUnitTest;
import cz.pa165.carpark.facade.ReservationSettingsFacadeImpl;
import cz.pa165.carpark.service.ReservationSettingsService;
import cz.pa165.carpark.util.ObjectMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * The tests for reservation settings facade.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
public class ReservationSettingsFacadeTest extends AbstractJUnitTest {

    @Mock
    private ObjectMapper mapper;

    @Mock
    private ReservationSettingsService reservationSettingsService;

    @InjectMocks
    private ReservationSettingsFacadeImpl reservationSettingsFacade;
}
