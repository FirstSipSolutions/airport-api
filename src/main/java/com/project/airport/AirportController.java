/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */


package com.project.airport;

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
@RequestMapping("/api/airports")
public class AirportController {

    // This will be injectoing the airport service to handle the actual logic
    // keeps the controller focused on just receiving requests

    @Autowired
    private AirportService airportService;



    // this will handle the get mapping to api/aiports
    // that will also return the list of aiports via json
    // hopefully

    @GetMapping
    public Iterable<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }
    // @PathVariable grabs the id from the url
    // returns the single matching airport
    @GetMapping("/{id}")
    public Airport getAirportById(@PathVariable Long id) {
        return airportService.getAirportById(id);
    }
    // @RequestBody converts the incoming JSON into an Airport object
    // saves it and returns it back with its generated id
    @PostMapping
    public Airport createAirport(@RequestBody Airport airport) {
        return airportService.createAirport(airport);
    }
   // this one will be  the mapping put to select the airport to change, body carries the new values
    // service loads it, applies changes, and saves
    @PutMapping("/{id}")
    public Airport updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        return airportService.updateAirport(id, airport);
    }
    // handles DELETE to /api/airports/id
    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
    }
}