package airport;



/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public Iterable<City> getAllCities() {
        return cityRepository.findAll();
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
        if (optionalCity.isPresent()) {
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