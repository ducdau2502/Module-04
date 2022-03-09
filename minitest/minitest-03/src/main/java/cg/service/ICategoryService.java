package cg.service;

import cg.model.Category;

import java.util.Optional;

public interface ICategoryService {
    Iterable<Category> findAllCategories();

    Optional<Category> findCategoryById(long id);

    Category save(Category category);

    void deleteCategoryById(long id);
}
