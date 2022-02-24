package service.Impl;

import model.Product;
import service.IProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService {

    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void update(Product product) {
        for (Product prod : products) {
            if (prod.getId() == product.getId()) {
                products.set(products.indexOf(prod), product);
            }
        }
    }

    @Override
    public void remove(Product product) {
        products.remove(product);
    }
}
