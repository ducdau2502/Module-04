package dd.repository;

import dd.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ICategoryRepository extends CrudRepository<Category, Long> {
}
