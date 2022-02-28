package dd.repository.Impl;

import dd.model.Product;
import dd.repository.IProductRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements IProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
//        String queryStr = "SELECT p FROM Product AS p ORDER BY p.id";
//        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
//        return query.getResultList();

        String sql ="Call Find_All()";
        Query query = entityManager.createNativeQuery(sql, Product.class);
        List<Product> products = query.getResultList();
        System.out.println(products);
        return products;
    }

    @Override
    public Product saveProduct(Product product) {

        if (product.getId() == 0) {

            String sql = "CALL Insert_Product(:image_product, :name_product, :price_product)";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("image_product", product.getImage());
            query.setParameter("name_product", product.getName());
            query.setParameter("price_product", product.getPrice());
            query.executeUpdate();
            return product;
        } else {
            return entityManager.merge(product);
        }
    }

    @Override
    public Product deleteProduct(int id) {
        Product product = findProductById(id);
        if (product != null) {
            entityManager.remove(product);
        }
        return product;
    }

    @Override
    public Product findProductById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> findByName(String name) {
        String queryStr = "SELECT p FROM Product as p WHERE p.name LIKE :name";
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
}
