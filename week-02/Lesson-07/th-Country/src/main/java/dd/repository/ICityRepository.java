package dd.repository;

import dd.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends PagingAndSortingRepository<City, Long> {
    Page<City> findAllByNameContaining(Pageable pageable, String name);

    Page<City> findAllByCountry_Id(Pageable pageable, Long id);

}
