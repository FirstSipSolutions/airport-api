/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */
package com.project.city;

// imprts here
// will add in some more imports for many to many
// that is an ongoing situation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin
@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;


    // adding a mapping as cities are not returning
    // this will require a get mapping for ALL cities, hopefully ...


    // added this so cities can be requested a page at a time instead of all at once
    // the Pageable gets filled from the url query params (?page= ?size= ?sort=)
    @GetMapping("/getall/paged")
    public ResponseEntity<Page<City>> getCitiesPaged(Pageable pageable) {
        Page<City> city = cityService.getCitiesPaged(pageable);
        if(city.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(city);
    }


    // the id in the url is pulled in by @PathVariable
    // returns just the one city that matches that id
    @GetMapping("/findby/id/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id) {
        City cityToReturn = cityService.getCityById(id);
        if(cityToReturn == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cityToReturn);
    }


    // here the body request will take json sent
    // it then turns all of that into city objecxt
    // once that is object it can be saved and returns that exact saved city
    // holding a new ID .,. in a perfect world

    // adding in a get mapping for returning all cities

    @GetMapping("/getall")
    public ResponseEntity<Iterable<City>> getAllCities() {
        Iterable<City> citiesToReturn = cityService.getAllCities();
        if(citiesToReturn == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(citiesToReturn);
    }

    @PostMapping("/createnew")
    public ResponseEntity<City> createCity(@RequestBody City city) {
        City newCityCreated = cityService.createCity(city);
        if(newCityCreated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newCityCreated);
    }


    // handles PUT to /api/cities/{id}


    @PutMapping("/update/id/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City city) {
        City cityToUpdate = cityService.getCityById(id);
        if(cityToUpdate == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cityToUpdate);
    }


    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id) {
        City cityToDelete = cityService.getCityById(id);
        if(cityToDelete == null){
            return ResponseEntity.notFound().build();
        }
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
