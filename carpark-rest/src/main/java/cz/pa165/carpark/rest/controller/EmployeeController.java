package cz.pa165.carpark.rest.controller;

import cz.pa165.carpark.api.dto.EmployeeDTO;
import cz.pa165.carpark.api.facade.EmployeeFacade;
import cz.pa165.carpark.rest.config.ApiConfiguration;
import cz.pa165.carpark.rest.exception.FailedOperationException;
import cz.pa165.carpark.rest.exception.MissingObjectException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

import static cz.pa165.carpark.rest.util.ApiUtils.notNull;

@RestController
@RequestMapping(ApiConfiguration.API_EMPLOYEES)
public class EmployeeController {

    private EmployeeFacade employeeFacade;

    @Inject
    public EmployeeController(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EmployeeDTO find(@PathVariable("id") Long id) {
        EmployeeDTO employee = employeeFacade.find(id);
        notNull(employee, MissingObjectException::new);

        return employee;
    }

    @RequestMapping(value = "/find-by-username/{username}", method = RequestMethod.GET)
    public EmployeeDTO findByUsername(@PathVariable("username") String username) {
        EmployeeDTO employee = employeeFacade.findByUsername(username);
        notNull(employee, MissingObjectException::new);

        return employee;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<EmployeeDTO> findAll() {
        return employeeFacade.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public EmployeeDTO create(@RequestBody EmployeeDTO employee) {
        EmployeeDTO result = employeeFacade.create(employee);
        notNull(result, FailedOperationException::new);

        return result;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public EmployeeDTO update(@RequestBody EmployeeDTO employee) {
        EmployeeDTO result = employeeFacade.update(employee);
        notNull(result, FailedOperationException::new);

        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        EmployeeDTO employee = employeeFacade.find(id);
        notNull(employee, MissingObjectException::new);

        employeeFacade.delete(id);
    }

}
