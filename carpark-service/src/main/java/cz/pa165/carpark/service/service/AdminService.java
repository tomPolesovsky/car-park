package cz.pa165.carpark.service.service;

import cz.pa165.carpark.persistence.entity.Employee;
import cz.pa165.carpark.service.exception.AuthenticationException;

/**
 * The admin facade's implementation.
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
public interface AdminService {

    /**
     * Login to the system with username & password
     *
     * @param username username
     * @param username password
     * @return employee
     * @throws AuthenticationException
     */
    Employee login(String username, String password);

}
