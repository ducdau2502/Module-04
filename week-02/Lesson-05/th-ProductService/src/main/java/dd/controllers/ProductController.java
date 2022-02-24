package dd.controllers;

import dd.model.Product;
import dd.model.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import dd.service.IProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @Value("${upload_image}")
    private String fileImage;
    @Value("${view}")
    private String view;

//    @ModelAttribute("products")
//    public List<Product> products(){
//        return productService.getAllProduct();
//    }

    @GetMapping("")
    public ModelAndView viewAll() {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Product> products = productService.getAllProduct();
        modelAndView.addObject("products", products);
        modelAndView.addObject("view", view);
        if (products.isEmpty()) {
            modelAndView.addObject("message", "No products!");
        }
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(Model model) {
        ModelAndView modelAndView = new ModelAndView("create");
        model.addAttribute("product", new Product());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute ProductForm productForm) {
        ModelAndView modelAndView = new ModelAndView("redirect:/product");
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileImage + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Product product = new Product(productForm.getName(), productForm.getPrice(), fileName);
        productService.saveProduct(product);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.getProduct(id);
        modelAndView.addObject("product", product);
        modelAndView.addObject("view", view);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@ModelAttribute ProductForm productForm, @PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/product");
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileImage + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Product product = new Product(id, productForm.getName(), productForm.getPrice(), fileName);
        productService.saveProduct(product);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/product");
        productService.deleteProduct(id);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Product product = productService.getProduct(id);
        modelAndView.addObject("product", product);
        modelAndView.addObject("view", view);
        return modelAndView;
    }

    @GetMapping("/find")
    public ModelAndView search(@RequestParam("search") String name) {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Product> products = productService.findByName(name);
        modelAndView.addObject("products", products);
        modelAndView.addObject("view", view);
        modelAndView.addObject("name", name);
        return modelAndView;
    }
}
