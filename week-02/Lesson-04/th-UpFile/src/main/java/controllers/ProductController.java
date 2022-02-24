package controllers;

import model.Product;
import model.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.IProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping("")
    public String index(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        System.out.println(fileUpload);
        return "/index";
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("productForm", new Product());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("create");
        Product product = productService.findById(id);
        modelAndView.addObject("productForm", product);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute ProductForm productForm) {
        ModelAndView modelAndView = new ModelAndView("redirect:/product");
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Product product = new Product(productForm.getId(), productForm.getName(), productForm.getDescription(), fileName);
        boolean flag = false;
        List<Product> products = productService.findAll();
        for (Product pro : products) {
            if (pro.getId() == product.getId()) {
                productService.update(product);
                flag = true;
            }
        }

        if (!flag) {
            product.setId((int) (Math.random() * 1000));
            productService.save(product);
        }
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView save(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/product");
        Product product = productService.findById(id);
        productService.remove(product);
        return modelAndView;
    }

}
