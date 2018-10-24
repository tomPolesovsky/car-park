package cz.pa165.carpark.util;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@ContextConfiguration(locations = "classpath:application-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractJUnitTest {

    @PersistenceContext
    protected EntityManager em;

}
