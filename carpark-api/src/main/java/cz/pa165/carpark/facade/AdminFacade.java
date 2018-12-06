package cz.pa165.carpark.facade;

import cz.pa165.carpark.dto.UserDTO;

/**
 * The admin facade's implementation.
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
public interface AdminFacade {

    /**
     * Login to the system with username & password
     *
     * @param username username
     * @param username password
     * @return UserDTO or null
     */
    UserDTO login(String username, String password);

}
