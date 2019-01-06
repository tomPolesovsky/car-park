package cz.pa165.carpark.api.facade;

import cz.pa165.carpark.api.dto.EmployeeDTO;

import java.util.List;

/**
 * The employee facade's interface.
 * Possibility to create, update, delete, find, find by username, find all (employee/s) via this facade.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 */
public interface EmployeeFacade {

    /**
     * Find the employee with the specified id
     *
     * @param id unambiguous identification of entity
     * @return employee dto
     */
    EmployeeDTO find(Long id);

    /**
     * Find the employee with the specified username
     *
     * @param username
     * @return employee dto
     */
    EmployeeDTO findByUsername(String username);

    /**
     * Find all employees
     *
     * @return list of employees' dtos
     */
    List<EmployeeDTO> findAll();

    /**
     * Create new employee
     *
     * @param employee dto
     * @return employee dto
     */
    EmployeeDTO create(EmployeeDTO employee);

    /**
     * Update the specified employee
     *
     * @param employee dto
     * @return employee dto
     */
    EmployeeDTO update(EmployeeDTO employee);

    /**
     * Delete the employee with the specified id
     *
     * @param id unambiguous identification of entity
     */
    void delete(Long id);

}
