package service.Impl;

import model.Product;
import service.IProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService {
    private static List<Product> products;

    static {
        products = new ArrayList<>();
        products.add( new Product(1, "iPhone X", 100, "Like New", "Apple"));
        products.add( new Product(2, "Galaxy S9", 50, "New", "Samsung"));
        products.add( new Product(3, "Edra384", 300, "99,99%", "China"));
        products.add( new Product(4, "Dell Vostro 12", 500, "Like New", "Dell"));
        products.add( new Product(5, "iPhone 13 Pro", 1000, "New", "Apple"));

    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product selectProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void createProduct(Product product) {
        products.add(product);
    }

    @Override
    public void deleteProductById(Product product) {
        products.remove(product);
    }

    @Override
    public void updateProductById(int index, Product product) {
        products.set(index, product);
    }
}
