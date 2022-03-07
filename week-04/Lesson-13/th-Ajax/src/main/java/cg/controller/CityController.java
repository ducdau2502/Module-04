package cg.controller;

import cg.model.City;
import cg.service.ICityService;
import cg.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/cities")
public class CityController {
    @Autowired
    ICityService cityService;

    @Autowired
    ICustomerService customerService;

    @GetMapping
    public ResponseEntity<Iterable<City>> showAll() {
        List<City> cities = (List<City>) cityService.findAllCity();
        if (cities.isEmpty()) {
            return new ResponseEntity<>(cities ,HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(cities, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable("id") long id) {
        Optional<City> cityOptional = cityService.findCityById(id);
        if (!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cityOptional.get(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<City> save(@RequestBody City city) {
        return new ResponseEntity<>(cityService.saveCity(city), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> update(@PathVariable("id") long id, @RequestBody City city) {
        Optional<City> cityOptional = cityService.findCityById(id);
        if (!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            city.setId(cityOptional.get().getId());
            city = cityService.saveCity(city);
            return new ResponseEntity<>(city, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> delete(@PathVariable("id") long id) {
        Optional<City> cityOptional = cityService.findCityById(id);
        if (!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            customerService.deleteAllByCity_Id(id);
            cityService.deleteCityById(id);
            return new ResponseEntity<>(cityOptional.get(), HttpStatus.OK);
        }
    }

}
