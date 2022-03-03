package dd.service;

import dd.model.Customer;

import java.util.Optional;

public interface ICustomerService {
    Iterable<Customer> findAll();

    Iterable<Customer> findAllByNameContaining(String search);

    Optional<Customer> findById(Long id);

    Customer save(Customer customer);

    void remove(Long id);
}
