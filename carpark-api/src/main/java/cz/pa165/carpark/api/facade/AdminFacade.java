package cz.pa165.carpark.api.facade;

import cz.pa165.carpark.api.dto.UserDTO;

/**
 * The admin facade's implementation.
 * Possibility to login to the system with username & password via this facade.
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
