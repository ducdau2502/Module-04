package cg.controller;

import cg.model.Product;
import cg.service.ICategoryService;
import cg.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> showAll(@RequestParam("search") Optional<String> search) {
        List<Product> products;
        if (search.isPresent()) {
            products = (List<Product>) productService.findAllByNameContaining(search.get());
        } else {
            products = (List<Product>) productService.findAllProduct();
        }

        if(products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable("id") long id) {
        Optional<Product> productOptional = productService.findProductById(id);

        if(!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable("id") long id) {
        Optional<Product> productOptional = productService.findProductById(id);
        if(!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            product.setId(productOptional.get().getId());
            product = productService.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") long id) {
        Optional<Product> productOptional = productService.findProductById(id);
        if(!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            productService.deleteProductById(id);
            return new ResponseEntity<>(productOptional.get(),HttpStatus.OK);
        }
    }

}
