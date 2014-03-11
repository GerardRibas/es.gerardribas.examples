package es.gerardribas.example.common.service;

import es.gerardribas.example.common.domain.Customer;

import java.util.List;

/**
 * Created by gerard on 08/03/2014.
 */
public interface CustomerService {
    Customer findCustomer(Long id);

    List<Customer> findAllCustomers();

    void insert(Customer customer);

    void update(Customer customer);

    void delete(Customer customer);
}
