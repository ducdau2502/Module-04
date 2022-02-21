package service;

import model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    Product selectProduct(int id);

    void createProduct(Product product);

    void deleteProductById(Product product);

    void updateProductById(int index, Product product);
}
