/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */

package com.project.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public Iterable<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        Optional<Airport> airport = airportRepository.findById(id);
        if (airport.isPresent()) {

            return airport.get();

        } else {
            return null;
        }
    }

    public Airport createAirport(Airport airport) {

        return airportRepository.save(airport);
    }

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

    public void deleteAirport(Long id) {

        airportRepository.deleteById(id);
    }
}