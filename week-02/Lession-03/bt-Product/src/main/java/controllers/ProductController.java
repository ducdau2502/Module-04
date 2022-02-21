package controllers;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import regex.Validate;
import service.IProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final Validate validate = new Validate();

    @Autowired
    private IProductService productService;

    @GetMapping("")
    public ModelAndView displayAll() {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Product> products = productService.findAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam("search") String search) {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Product> productList = new ArrayList<>();
        for(Product product : productService.findAll()) {
            if (validate.validateTitleOrCategory(search, product.getName())) {
                productList.add(product);
            }
        }
        modelAndView.addObject("products", productList);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createGet() {
        ModelAndView modelAndView = new ModelAndView("create");
        Product product = new Product();
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editGet(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("create");
        Product product = productService.selectProduct(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("/{id}/view")
    public ModelAndView viewDetail(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Product product = productService.selectProduct(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute Product product) {
        ModelAndView modelAndView = new ModelAndView("redirect:/product");
        boolean flag = false;
        List<Product> products = productService.findAll();
        for (Product pro : products) {
            if (pro.getId() == product.getId()) {
                productService.updateProductById(products.indexOf(pro), product);
                flag = true;
            }
        }
        if (!flag) {
            product.setId((int) (Math.random() * 1000));
            productService.createProduct(product);
        }
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/product");
        Product product = productService.selectProduct(id);
        productService.deleteProductById(product);
        return modelAndView;
    }
}
