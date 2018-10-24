package cz.pa165.carpark.dao;

import cz.pa165.carpark.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class EmployeeDaoImpl extends DaoImpl<Employee> implements EmployeeDao {

    public EmployeeDaoImpl() {
        super(Employee.class);
    }

    @Override
    public Employee findByUsername(String username) {
        return null;
    }

}
