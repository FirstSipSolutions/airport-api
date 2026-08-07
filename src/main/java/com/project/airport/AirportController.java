/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */


package com.project.airport;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;

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

    @GetMapping("/getall")
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> listOfAirports = airportService.getAllAirports();
        if(listOfAirports == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listOfAirports);
    }


    // @PathVariable grabs the id from the url
    // returns the single matching airport
    @GetMapping("/findby/id/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        Airport airportToReturnById = airportService.getAirportById(id);
        if(airportToReturnById == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(airportToReturnById);
    }


    // @RequestBody converts the incoming JSON into an Airport object
    // saves it and returns it back with its generated id
    @PostMapping("/createnew")
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        Airport newAirport = airportService.createAirport(airport);
        if(newAirport == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newAirport);
    }


   // this one will be  the mapping put to select the airport to change, body carries the new values
    // service loads it, applies changes, and saves
    @PutMapping("/update/id/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        Airport airportToUpdate = airportService.updateAirport(id,airport);
        if(airportToUpdate == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(airportToUpdate);
    }


    // handles DELETE to /api/airports/delete/id/{id}
    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Airport> deleteAirport(@PathVariable Long id) {
        Airport airportToDelete = airportService.deleteAirport(id);
        if(airportToDelete == null){
            return ResponseEntity.notFound().build();
        }
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }
}