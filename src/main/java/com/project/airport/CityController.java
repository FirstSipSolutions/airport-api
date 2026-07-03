/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */
package com.project.airport;

// imprts here
// will add in some more imports for many to many
// that is an ongoing situation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;

<<<<<<< Updated upstream

    // adding a mapping as cities are not returning
    // this will require a get mapping for ALL cities, hopefully ..






=======
    // mewest addition for pagination here
>>>>>>> Stashed changes

    // added this so cities can be requested a page at a time instead of all at once
    // the Pageable gets filled from the url query params (page size  sort)
    @GetMapping("/paged")
    public Page<City> getCitiesPaged(Pageable pageable) {
        return cityService.getCitiesPaged(pageable);
    }


    // the id in the url is pulled in by @PathVariable
    // returns just the one city that matches that id
    @GetMapping("/{id}")
    public City getCityById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }


<<<<<<< Updated upstream

// here the body request will take json sent
=======
    // here the body request will take json sent
>>>>>>> Stashed changes
    // it then turns all of that into city objecxt
    //once that is object it can be saved and returns that exact saved city
    // holding a new ID .,. in a perfect world



    // adding in a get mapping for returning all cities

    @GetMapping
    public Iterable<City> getAllCities() {
        return cityService.getAllCities();
    }





    @PostMapping
    public City createCity(@RequestBody City city) {
        return cityService.createCity(city);
    }
    // handles PUT to /api/cities/{id}

    @PutMapping("/{id}")
    public City updateCity(@PathVariable Long id, @RequestBody City city) {
        return cityService.updateCity(id, city);
    }


    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
    }
}
