package dd.controllers;

import dd.model.City;
import dd.model.Country;
import dd.service.ICityService;
import dd.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/cities")
public class CityController {

    @Autowired
    ICityService cityService;

    @Autowired
    ICountryService countryService;

    @Value("${upload_image}")
    private String fileImage;

    @Value("${view}")
    private String view;


    @GetMapping("")
    public ModelAndView showAll(@RequestParam("search") Optional<String> search, @PageableDefault(value = 3) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<City> cityPage;
        if(search.isPresent()){
            cityPage = cityService.findAllByNameContaining(pageable, search.get());
            modelAndView.addObject("search", search.get());
        } else {
            cityPage = cityService.findAll(pageable);
        }
        modelAndView.addObject("search", search);
        modelAndView.addObject("cities", cityPage);
        modelAndView.addObject("view", view);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("city", new City());
        Iterable<Country> countries = countryService.findAll();
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute City city) {
        ModelAndView modelAndView = new ModelAndView("redirect:/cities");
        MultipartFile multipartFile = city.getImageFile();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(city.getImageFile().getBytes(), new File(fileImage + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        city.setImage(fileName);
        cityService.save(city);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        City city = null;
        if (cityService.findById(id).isPresent()) {
            city = cityService.findById(id).get();
        }
        modelAndView.addObject("city", city);
        modelAndView.addObject("view", view);
        Iterable<Country> countries = countryService.findAll();
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView save(@ModelAttribute City city, @PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/cities");
        if (city.getImageFile().getSize() != 0) {
            MultipartFile multipartFile = city.getImageFile();
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(city.getImageFile().getBytes(), new File(fileImage + fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            city.setImage(fileName);
        } else {
            city.setImage(cityService.findById(id).get().getImage());
        }

        city.setId(id);
        cityService.save(city);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/cities");
        cityService.remove(id);
        return modelAndView;
    }
}
