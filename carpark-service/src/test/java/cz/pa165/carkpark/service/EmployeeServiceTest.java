package cz.pa165.carkpark.service;

import cz.pa165.carkpark.util.AbstractJUnitTest;
import cz.pa165.carpark.dao.EmployeeDao;
import cz.pa165.carpark.service.EmployeeServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * The tests for employee service.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
public class EmployeeServiceTest extends AbstractJUnitTest {

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeServiceImpl employeeService;
}
