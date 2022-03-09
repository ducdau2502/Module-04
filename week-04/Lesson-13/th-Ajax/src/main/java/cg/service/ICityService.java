package cg.service;

import cg.model.City;

import java.util.Optional;

public interface ICityService {
    Iterable<City> findAllCity();

    Optional<City> findCityById(long id);

    City saveCity(City city);

    void deleteCityById(long id);

    Iterable<City> findAllByNameContaining(String name);
}
