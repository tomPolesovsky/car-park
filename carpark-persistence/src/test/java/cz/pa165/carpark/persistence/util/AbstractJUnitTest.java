package cz.pa165.carpark.persistence.util;

import cz.pa165.carpark.persistence.config.PersistenceConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ContextConfiguration(classes = PersistenceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractJUnitTest {

    @PersistenceContext
    protected EntityManager em;

}
