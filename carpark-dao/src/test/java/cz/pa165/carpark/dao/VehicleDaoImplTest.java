package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Vehicle;
import cz.pa165.carpark.util.AbstractJUnitTest;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Tests for Vehicle' DAO implementation.
 *
 * @author Tomáš Polešovský, 487574@mail.muni.cz
 */
@Transactional
public class VehicleDaoImplTest extends AbstractJUnitTest {

    @Inject
    private VehicleDao vehicleDao;

    @Test
    public void find() {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber("aabbcc");
        em.persist(vehicle);

        Vehicle vehicleResult = vehicleDao.find(vehicle.getId());
        assertThat("aabbcc", is(vehicleResult.getRegistrationNumber()));

        Vehicle vehicleNull = vehicleDao.find(10L);
        assertNull(vehicleNull);
    }

    @Test
    public void findByRegistrationNumber() {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber("aabbcc");
        em.persist(vehicle);

        Vehicle vehicleResult = vehicleDao.findByRegistrationNumber(
                vehicle.getRegistrationNumber()
        );
        assertThat("aabbcc", is(vehicleResult.getRegistrationNumber()));
    }

    @Test(expected = DataAccessException.class)
    public void findByRegistrationNumberNotExists() {
        vehicleDao.findByRegistrationNumber("aasff");
    }

    @Test
    public void findAll() {
        Vehicle firstVehicle = new Vehicle();
        firstVehicle.setRegistrationNumber("aabbcc");
        em.persist(firstVehicle);

        Vehicle secondVehicle = new Vehicle();
        secondVehicle.setRegistrationNumber("bbccdd");
        em.persist(secondVehicle);

        List<Vehicle> vehicleResult = vehicleDao.findAll();
        assertThat(vehicleResult, hasSize(2));
        assertThat(vehicleResult, contains(
                hasProperty("registrationNumber",
                        is("aabbcc")),
                hasProperty("registrationNumber",
                        is("bbccdd"))
        ));
    }

    @Test
    public void save() {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber("aabbcc");
        vehicleDao.save(vehicle);

        Vehicle vehicleResult = em.find(Vehicle.class, vehicle.getId());
        assertEquals("aabbcc", vehicleResult.getRegistrationNumber());
    }

    @Test
    public void update() {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber("aabbcc");
        vehicle.setBrand("Škoda");
        em.persist(vehicle);

        Vehicle foundVehicle = em.find(Vehicle.class, vehicle.getId());
        foundVehicle.setBrand("BMW");
        vehicleDao.update(foundVehicle);

        Vehicle vehicleResult = em.find(Vehicle.class, vehicle.getId());
        assertEquals("BMW", vehicleResult.getBrand());
    }

    @Test
    public void delete() {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber("aabbcc");
        em.persist(vehicle);

        vehicleDao.delete(vehicle.getId());

        Vehicle vehicleResult = em.find(Vehicle.class, vehicle.getId());
        assertNull(vehicleResult);
    }

}
