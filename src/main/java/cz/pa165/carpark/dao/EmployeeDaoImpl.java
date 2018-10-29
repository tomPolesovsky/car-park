package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 * Specific implementation of {@link EmployeeDao} interface
 *
 * @param <T> generic type annotated by {@link Entity}
 * @author Tomáš Polešovský, 487574@mail.muni.cz
 */
@Transactional
@Repository
public class EmployeeDaoImpl extends DaoImpl<Employee> implements EmployeeDao {

    public EmployeeDaoImpl() {
        super(Employee.class);
    }

    @Override
    public Employee findByUsername(String username) {
        try{
            return em.createQuery("from Employee where username = :username", Employee.class)
                    .setParameter("username", username)
                    .getSingleResult();
        }catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

}
