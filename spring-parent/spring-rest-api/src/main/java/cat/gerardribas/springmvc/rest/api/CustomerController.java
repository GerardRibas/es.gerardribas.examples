package cat.gerardribas.springmvc.rest.api;

import cat.gerardribas.springmvc.rest.api.exception.ResourceNotFoundException;
import es.gerardribas.example.common.domain.Customer;
import es.gerardribas.example.common.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gerard on 08/03/2014.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService service;

    @RequestMapping
    public
    @ResponseBody
    List<Customer> findCustomers() {
        return service.findAllCustomers();
    }

    @RequestMapping("/{id}")
    public
    @ResponseBody
    Customer findCustomerById(@PathVariable Long id) {
        Customer customer = service.findCustomer(id);
        if (customer == null) {
            throw new ResourceNotFoundException();
        }
        return customer;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Long newCustomer(@RequestBody Customer entity) {
        service.insert(entity);
        return entity.getId();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@RequestBody Customer entity) {
        service.update(entity);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@RequestBody Customer entity) {
        service.delete(entity);
    }

    @Resource
    public void setService(CustomerService service) {
        this.service = service;
    }

}
