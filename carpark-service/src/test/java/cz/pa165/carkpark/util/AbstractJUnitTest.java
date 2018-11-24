package cz.pa165.carkpark.util;

import cz.pa165.carpark.config.ServiceConfiguration;
import cz.pa165.carpark.util.ObjectMapper;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractJUnitTest {
}
