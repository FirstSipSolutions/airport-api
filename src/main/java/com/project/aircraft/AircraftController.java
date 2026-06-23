package com.project.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
@CrossOrigin
public class AircraftController {
    @Autowired
    private AircraftService aircraftService;

    @GetMapping
    public ResponseEntity<List<Aircraft>> getAllAircraft() {
        List<Aircraft> aircraft = aircraftService.getAllAircraft();
        return ResponseEntity.ok(aircraft);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aircraft> getAircraftById(@PathVariable long id) {
        Aircraft aircraftToReturn = aircraftService.getAircraftById(id);

        if(aircraftToReturn == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aircraftToReturn);
    }

    @PostMapping
    public ResponseEntity<Aircraft> createAircraft(@RequestBody Aircraft aircraft){
        Aircraft newAircraft = aircraftService.createAircraft(aircraft);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAircraft);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aircraft> updateAircraft(@PathVariable long id, @RequestBody Aircraft aircraft) {
        Aircraft existingAircraft = aircraftService.getAircraftById(id);

        if(existingAircraft == null){
            return ResponseEntity.notFound().build();
        }

        Aircraft updatedAircraft = aircraftService.updateAircraft(id, aircraft);
        return ResponseEntity.ok(updatedAircraft);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aircraft> deleteAircraft(@PathVariable long id) {
        Aircraft aircraftToDelete =  aircraftService.getAircraftById(id);

        if(aircraftToDelete == null){
            return ResponseEntity.notFound().build();
        }
        aircraftService.deleteAircraftById(id);
        return ResponseEntity.noContent().build();
    }

}
