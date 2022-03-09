package cg.service;

import cg.model.Product;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAllProduct();

    Optional<Product> findProductById(long id);

    Product save(Product product);

    void deleteProductById(long id);

    Iterable<Product> findAllByNameContaining(String name);

    Iterable<Product> findAllByCategory_Id(long id);

    void deleteAllByCategory_Id(long id);
}
