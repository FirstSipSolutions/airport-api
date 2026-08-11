package com.project.flight;

import com.project.passenger.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/findall")
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> listOfFlights = flightService.getAllFlights();
        if(listOfFlights == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listOfFlights);
    }

    @GetMapping("/findby/id/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flightToReturn = flightService.getFlightById(id);
        if(flightToReturn == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flightToReturn);
    }

    @GetMapping("/departure/airportid/{airportId}")
    public ResponseEntity<List<Flight>> getDeparturesByAirportId(@PathVariable Long airportId) {
        List<Flight> departures = flightService.getDepartures(airportId);
        if(departures == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(departures);
    }

    @GetMapping("/arrival/airportid/{airportId}")
    public ResponseEntity<List<Flight>> getArrivalsByAirportId(@PathVariable Long airportId) {
        List<Flight> arrivals = flightService.getArrivals(airportId);
        if(arrivals == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(arrivals);
    }

    @PostMapping("/createnew")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight newFlight = flightService.createFlight(flight);
        if(newFlight == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newFlight);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        Flight flightToUpdate = flightService.updateFlight(id, flight);
        if(flightToUpdate == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flightToUpdate);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Flight> deleteFlight(@PathVariable Long id) {
        Flight flightToDelete = flightService.getFlightById(id);
        if(flightToDelete == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
