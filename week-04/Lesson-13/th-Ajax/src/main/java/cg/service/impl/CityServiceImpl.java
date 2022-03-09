package cg.service.impl;

import cg.model.City;
import cg.repository.ICityRepository;
import cg.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private ICityRepository cityRepository;

    @Override
    public Iterable<City> findAllCity() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findCityById(long id) {
        return cityRepository.findById(id);
    }

    @Override
    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void deleteCityById(long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public Iterable<City> findAllByNameContaining(String name) {
        return cityRepository.findAllByNameContaining(name);
    }
}
