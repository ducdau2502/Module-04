package cg.service.impl;

import cg.model.Customer;
import cg.repository.ICustomerRepository;
import cg.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findCustomerById(long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void deleteAllByCity_Id(long id) {
        customerRepository.deleteAllByCity_Id(id);
    }

}
