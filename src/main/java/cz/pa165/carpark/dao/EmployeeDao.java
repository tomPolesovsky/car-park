package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Employee;

public interface EmployeeDao extends Dao<Employee> {

    Employee findByUsername(String username);

}
