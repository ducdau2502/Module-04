package cg.controller;

import cg.model.Category;
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
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> showAll() {
        List<Category> categories = (List<Category>) categoryService.findAllCategories();

        if(categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable("id") long id) {
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        
        if(!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable("id") long id) {
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if(!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            category.setId(categoryOptional.get().getId());
            category = categoryService.save(category);
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") long id) {
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if(!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            productService.deleteAllByCategory_Id(id);
            categoryService.deleteCategoryById(id);
            return new ResponseEntity<>(categoryOptional.get(),HttpStatus.OK);
        }
    }

}
