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


    // getting mapping here returns all of the cities
    @GetMapping
    public Iterable<City> getAllCities() {
        return cityService.getAllCities();
    }
    // handles GET to /api/cities/{id}
    // pretty sure that will run smoothly

    // the id in the url is pulled in by @PathVariable
    // returns just the one city that matches that id
    @GetMapping("/{id}")
    public City getCityById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }
// here the body request will take json sent
    // it then turns all of that into city objecxt
    //once that is object it can be saved and returns that exact saved city
    // holding a new ID .,. in a perfect world
    @PostMapping
    public City createCity(@RequestBody City city) {
        return cityService.createCity(city);
    }
    // handles PUT to /api/cities/{id}

    @PutMapping("/{id}")
    public City updateCity(@PathVariable Long id, @RequestBody City city) {
        return cityService.updateCity(id, city);
    }

    // simple delete again
    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
    }
}
