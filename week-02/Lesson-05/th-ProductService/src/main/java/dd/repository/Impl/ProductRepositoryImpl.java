package dd.repository.Impl;

import dd.model.Product;
import dd.repository.IProductRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements IProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        String queryStr = "SELECT p FROM Product AS p ORDER BY p.id";
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        return query.getResultList();
    }

    @Override
    public Product saveProduct(Product product) {

            if (product.getId() == 0) {
                 entityManager.persist(product);
            } else {
                 entityManager.merge(product);
            }
        return product;
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
        String queryStr = "SELECT p FROM Product as p WHERE p.id = :id";
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Product> findByName(String name) {
        String queryStr = "SELECT p FROM Product as p WHERE p.name LIKE :name";
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        query.setParameter("name", "%"+name+"%");
        return query.getResultList();
    }
}
