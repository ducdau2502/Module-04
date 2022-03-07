package cg.repository;

import cg.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ICityRepository extends CrudRepository<City, Long> {
}
