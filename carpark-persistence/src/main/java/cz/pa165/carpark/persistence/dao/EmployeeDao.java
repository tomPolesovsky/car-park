package cz.pa165.carpark.persistence.dao;

import cz.pa165.carpark.persistence.entity.Employee;

/**
 * Extended {@link Dao} for Employee
 *
 * @author Tomáš Polešovský, 487574@mail.muni.cz
 */
public interface EmployeeDao extends Dao<Employee> {

    /**
     * Find specific employee by its username
     *
     * @param username username of employee
     * @return entity object
     */
    Employee findByUsername(String username);

}
