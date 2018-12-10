package cz.pa165.carpark.controller;

import cz.pa165.carpark.config.ApiConfiguration;
import cz.pa165.carpark.dto.EmployeeDTO;
import cz.pa165.carpark.exception.FailedOperationException;
import cz.pa165.carpark.exception.MissingObjectException;
import cz.pa165.carpark.facade.EmployeeFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.inject.Inject;

import static cz.pa165.carpark.util.ApiUtils.notNull;

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

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
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
