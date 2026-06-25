/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */

package com.project.airport;

// imports for spring and such
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;



// first part of this service class
@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;


    // here the code grabs from repoistory andreturnss
    // all airports, from the repo get taken  in

    public Iterable<Airport> getAllAirports() {
        return airportRepository.findAll();
    }



    // looking for the aiport byu  id
    // if it is there it will return it, if not returns null

    public Airport getAirportById(Long id) {
        Optional<Airport> airport = airportRepository.findById(id);
        if (airport.isPresent()) {

            return airport.get();

        } else {
            return null;
        }
    }
    // save a new aiport  and returns back the same
    // airport via the created airport feature
    // adding in as many comments as I can
    public Airport createAirport(Airport airport) {

        return airportRepository.save(airport);
    }


    // this part will update the existing airport
    // pulls the repo and copies new num and then saves that value
    // this has a handler as well for reutrning null if no vlaue


    // that null will be important as a handler to see what goes wrong, as this is not a bulletproof section of code
    // commited in chunks and commented after the fact
    public Airport updateAirport(Long id, Airport airportDetails) {
        Optional<Airport> optionalAirport = airportRepository.findById(id);
        if (optionalAirport.isPresent()) {


            Airport airport = optionalAirport.get();
            airport.setName(airportDetails.getName());
            airport.setCode(airportDetails.getCode());
            airport.setCity(airportDetails.getCity());
            return airportRepository.save(airport);
        }

        else {


            return null;
        }
    }


    // this is a simple deletion
    public void deleteAirport(Long id) {

        airportRepository.deleteById(id);
    }
}