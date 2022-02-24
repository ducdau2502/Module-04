package dd.service;

import dd.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProduct();

    Product saveProduct(Product product);

    Product deleteProduct(int id);

    Product getProduct(int id);

    List<Product> findByName(String name);
}
