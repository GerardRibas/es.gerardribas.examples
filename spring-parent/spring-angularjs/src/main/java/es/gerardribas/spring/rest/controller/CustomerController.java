/**
 *
 */
package es.gerardribas.spring.rest.controller;

import es.gerardribas.example.common.dao.CustomerDao;
import es.gerardribas.example.common.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gerard
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(value = "/customer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    List<Customer> findAll() {
        List<Customer> customers = customerDao.findAll(Customer.class);
        return customers;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        customerDao.persist(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>(
                "The String ResponseBody with custom header Content-Type=application/json",
                headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCustomer(@PathVariable Long id,
                                                 @RequestBody Customer customer) {
        customerDao.update(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>(
                "The String ResponseBody with custom header Content-Type=application/json",
                headers, HttpStatus.OK);

    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerDao.findById(Customer.class, id);
        customerDao.remove(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>(
                "The String ResponseBody with custom header Content-Type=application/json",
                headers, HttpStatus.OK);

    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Customer findById(@PathVariable Long id) {
        return customerDao.findById(Customer.class, id);
    }

}
