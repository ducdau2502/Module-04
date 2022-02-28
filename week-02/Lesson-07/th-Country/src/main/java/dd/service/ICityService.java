package dd.service;

import dd.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICityService {
    Page<City> findAll(Pageable pageable);

    Page<City> findAllByNameContaining(Pageable pageable, String name);

    Page<City> findAllByCountry_Id(Pageable pageable, Long id);

    Optional<City> findById(Long id);

    void save(City city);

    void remove(Long id);
}
