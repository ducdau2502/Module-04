package cg.service;

import cg.model.Customer;

import java.util.Optional;

public interface ICustomerService {
    Iterable<Customer> findAllCustomer();

    Optional<Customer> findCustomerById(long id);

    Customer saveCustomer(Customer customer);

    void deleteCustomerById(long id);

    void deleteAllByCity_Id(long id);

}