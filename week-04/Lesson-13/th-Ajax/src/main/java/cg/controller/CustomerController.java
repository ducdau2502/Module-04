package cg.controller;

import cg.model.Customer;
import cg.service.ICityService;
import cg.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    ICityService cityService;

    @Autowired
    ICustomerService customerService;

    @GetMapping
    public ResponseEntity<Iterable<Customer>> showAll() {
        List<Customer> customers = (List<Customer>) customerService.findAllCustomer();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(customers, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") long id) {
        Optional<Customer> customerOptional = customerService.findCustomerById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable("id") long id, @RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerService.findCustomerById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            customer.setId(customerOptional.get().getId());
            customer = customerService.saveCustomer(customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable("id") long id) {
        Optional<Customer> customerOptional = customerService.findCustomerById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            customerService.deleteCustomerById(id);
            return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
        }
    }
}
