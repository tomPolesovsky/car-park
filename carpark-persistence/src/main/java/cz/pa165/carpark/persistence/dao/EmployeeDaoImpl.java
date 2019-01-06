package cz.pa165.carpark.persistence.dao;

import cz.pa165.carpark.persistence.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

/**
 * Specific implementation of {@link EmployeeDao} interface
 *
 * @author Tomáš Polešovský, 487574@mail.muni.cz
 */
@Repository
public class EmployeeDaoImpl extends DaoImpl<Employee> implements EmployeeDao {

    public EmployeeDaoImpl() {
        super(Employee.class);
    }

    /**
     * Find specific employee by its username
     *
     * @param username username of employee
     * @return entity object
     */
    @Override
    public Employee findByUsername(String username) {
        return em.createQuery("from Employee where username = :username", Employee.class)
                .setParameter("username", username)
                .getSingleResult();
    }

}
