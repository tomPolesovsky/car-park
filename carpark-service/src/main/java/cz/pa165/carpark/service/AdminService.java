package cz.pa165.carpark.service;

import cz.pa165.carpark.entity.Employee;
import cz.pa165.carpark.exception.AuthenticationException;

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
