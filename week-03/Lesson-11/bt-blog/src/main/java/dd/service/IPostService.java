package dd.service;

import dd.model.Category;
import dd.model.Post;

import java.util.Optional;

public interface IPostService {
    Iterable<Post> findAll();

    Iterable<Post> findAllByCategory(Category category);

    Iterable<Post> findAllByCategoryId(long id);

    void deleteAllByCategory(Category category);

    void deleteAllByCategoryId(long id);

    Optional<Post> findById(long id);

    Post save(Post post);

    void remove(Long id);
}
