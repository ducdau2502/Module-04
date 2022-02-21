package controllers;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.ICustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public String index(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "/index";
    }

    @GetMapping("/create")
    public String createGet(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "/create";
    }

    @PostMapping("/save")
    public String createPost(@ModelAttribute Customer customer) {
        boolean check = false;
        List<Customer> customers = customerService.findAll();
        for (Customer cus: customers) {
            if(cus.getId() == customer.getId()) {
                customerService.update(customers.indexOf(cus), customer);
                check = true;
            }
        }
        if (!check) {
            customer.setId((int) (Math.random() * 10000));
            customerService.save(customer);
        }
        return "redirect:/customer";
    }

    @GetMapping("/{id}/edit")
    public String editGet(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "/create";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        Customer customer = customerService.findById(id);
        customerService.remove(customer);
        return "redirect:/customer";
    }

    @GetMapping("/{id}/view")
    public String viewDetail(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "/detail";
    }

}
