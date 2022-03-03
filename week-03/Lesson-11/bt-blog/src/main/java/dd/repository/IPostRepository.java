package dd.repository;

import dd.model.Category;
import dd.model.Post;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IPostRepository extends PagingAndSortingRepository<Post, Long> {
    Iterable<Post> findAllByCategory(Category category);

    Iterable<Post> findAllByCategoryId(long id);

    void deleteAllByCategory(Category category);

    void deleteAllByCategoryId(long id);
}
