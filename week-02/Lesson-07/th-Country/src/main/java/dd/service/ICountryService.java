package dd.service;

import dd.model.Country;

import java.util.Optional;

public interface ICountryService {
    Iterable<Country> findAll();

    Optional<Country> findById(Long id);

    void delete(long id);
}
