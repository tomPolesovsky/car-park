package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Employee;

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
