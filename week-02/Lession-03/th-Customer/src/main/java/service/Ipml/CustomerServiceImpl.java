package service.Ipml;

import model.Customer;
import org.springframework.stereotype.Service;
import service.ICustomerService;

import java.util.ArrayList;
import java.util.List;

//@Service
public class CustomerServiceImpl implements ICustomerService {
    private static final List<Customer> customers;

    static {

        customers = new ArrayList<>();
        customers.add(new Customer(1, "John", "john@codegym.vn", "Hanoi"));
        customers.add(new Customer(2, "Bill", "bill@codegym.vn", "Danang"));
        customers.add(new Customer(3, "Alex", "alex@codegym.vn", "Saigon"));
        customers.add(new Customer(4, "Adam", "adam@codegym.vn", "Beijin"));
        customers.add(new Customer(5, "Sophia", "sophia@codegym.vn", "Miami"));
        customers.add(new Customer(6, "Rose", "rose@codegym.vn", "Newyork"));
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public void save(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Customer findById(int id) {
        Customer customer = null;
        for (Customer cus: customers) {
            if (cus.getId() == id) {
                customer = cus;
            }
        }
        return customer;
    }

    @Override
    public void update(int id, Customer customer) {
        customers.set(id, customer);
    }

    @Override
    public void remove(Customer customer) {
        customers.remove(customer);
    }
}
