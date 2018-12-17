package cz.pa165.carpark.rest.controller;

import cz.pa165.carpark.api.dto.EmployeeDTO;
import cz.pa165.carpark.api.facade.EmployeeFacade;
import cz.pa165.carpark.rest.config.ApiConfiguration;
import cz.pa165.carpark.rest.exception.FailedOperationException;
import cz.pa165.carpark.rest.exception.MissingObjectException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

import static cz.pa165.carpark.rest.util.ApiUtils.notNull;

/**
 * Employee REST API
 */
@RestController
@RequestMapping(ApiConfiguration.API_EMPLOYEES)
public class EmployeeController {

    private EmployeeFacade employeeFacade;

    @Inject
    public EmployeeController(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    /**
     * Find employee by its id.
     *
     * @param id
     * @return employee dto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EmployeeDTO find(@PathVariable("id") Long id) {
        EmployeeDTO employee = employeeFacade.find(id);
        notNull(employee, MissingObjectException::new);

        return employee;
    }

    /**
     * Find employee with the specified username.
     *
     * @param username dto
     * @return employee dto
     */
    @RequestMapping(value = "/find-by-username/{username}", method = RequestMethod.GET)
    public EmployeeDTO findByUsername(@PathVariable("username") String username) {
        EmployeeDTO employee = employeeFacade.findByUsername(username);
        notNull(employee, MissingObjectException::new);

        return employee;
    }

    /**
     * Find all employees.
     *
     * @return list of employee dto
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<EmployeeDTO> findAll() {
        return employeeFacade.findAll();
    }

    /**
     * Create an employee.
     *
     * @param employee dto
     * @return employee dto
     */
    @RequestMapping(method = RequestMethod.POST)
    public EmployeeDTO create(@Valid @RequestBody EmployeeDTO employee) {
        EmployeeDTO result = employeeFacade.create(employee);
        notNull(result, FailedOperationException::new);

        return result;
    }

    /**
     * Update an employee.
     *
     * @param employee dto
     * @return employee dto
     */
    @RequestMapping(method = RequestMethod.PUT)
    public EmployeeDTO update(@Valid @RequestBody EmployeeDTO employee) {
        EmployeeDTO result = employeeFacade.update(employee);
        notNull(result, FailedOperationException::new);

        return result;
    }

    /**
     * Delete an employee with the specified id.
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        EmployeeDTO employee = employeeFacade.find(id);
        notNull(employee, MissingObjectException::new);

        employeeFacade.delete(id);
    }
}
