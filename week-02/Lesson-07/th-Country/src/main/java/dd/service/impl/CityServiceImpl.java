package dd.service.impl;

import dd.model.City;
import dd.model.Country;
import dd.repository.ICityRepository;
import dd.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    ICityRepository cityRepository;

    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override
    public Page<City> findAllByCountry_Id(Pageable pageable, Long id) {
        return cityRepository.findAllByCountry_Id(pageable, id);
    }

    @Override
    public Page<City> findAllByNameContaining(Pageable pageable, String name) {
        return cityRepository.findAllByNameContaining(pageable, name);
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public void remove(Long id) {
        cityRepository.deleteById(id);
    }

}
