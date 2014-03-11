package es.gerardribas.example.common.service.impl;

import es.gerardribas.example.common.dao.CustomerDao;
import es.gerardribas.example.common.domain.Customer;
import es.gerardribas.example.common.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gerard on 08/03/2014.
 */
@Service
public class CustomerServiceImpl implements CustomerService {


    private CustomerDao customerDao;

    @Override
    public Customer findCustomer(Long id) {
        return customerDao.findById(Customer.class, id);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerDao.findAll(Customer.class);
    }

    @Override
    public void insert(Customer customer) {
        customerDao.persist(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerDao.remove(customer);
    }

    @Resource
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}
