package dd.service;

import dd.model.Category;

import java.util.Optional;

public interface ICategoryService {
    Iterable<Category> findAll();

    Optional<Category> findById(long id);

    Category save(Category category);

    void deleteById(long id);
}
