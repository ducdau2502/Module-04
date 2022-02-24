package dd.repository;

import dd.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();

    Product saveProduct(Product product);

    Product deleteProduct(int id);

    Product findProductById(int id);

    List<Product> findByName(String name);
}
