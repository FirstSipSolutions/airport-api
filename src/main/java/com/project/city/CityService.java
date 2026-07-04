

/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */

package com.project.city;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

// this is added because the cities were not being called and showed in Postman.
    public Iterable<City> getAllCities() {
        return cityRepository.findAll();
    }

    // Spring fills the Pageable in from the request, then it is passed to findAll.
    public Page<City> getCitiesPaged(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }


    // long as the datatype for id
    // added if statement for if present or not
    // if not will return null
    public City getCityById(Long id) {
        Optional<City> city = cityRepository.findById(id);
        if (city.isPresent()) {

            return city.get();
        } else {

            return null;
        }
    }


    //creating city
    //will return city from repo
    public City createCity(City city) {


        return cityRepository.save(city);
    }

    public City updateCity(Long id, City cityDetails) {
        Optional<City> optionalCity = cityRepository.findById(id);


        // only update if a city with that id was actually found

        if (optionalCity.isPresent()) {

            // copys the new values from the incoming details onto the existing city

            City city = optionalCity.get();
            city.setName(cityDetails.getName());
            city.setState(cityDetails.getState());
            city.setPopulation(cityDetails.getPopulation());


            return cityRepository.save(city);


        } else {
            return null;

        }
    }

    public void deleteCity(Long id) {

        cityRepository.deleteById(id);
    }
}