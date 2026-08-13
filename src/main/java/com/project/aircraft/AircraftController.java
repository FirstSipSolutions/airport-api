package com.project.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
@CrossOrigin
public class AircraftController {
    @Autowired
    private AircraftService aircraftService;

    @GetMapping("/findall")
    public ResponseEntity<List<Aircraft>> getAllAircraft() {
        List<Aircraft> aircraft = aircraftService.getAllAircraft();
        return ResponseEntity.ok(aircraft);
    }

    @GetMapping("/findby/id/{id}")
    public ResponseEntity<Aircraft> getAircraftById(@PathVariable long id) {
        Aircraft aircraftToReturn = aircraftService.getAircraftById(id);

        if(aircraftToReturn == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aircraftToReturn);
    }

    @PostMapping("/createnew")
    public ResponseEntity<Aircraft> createAircraft(@RequestBody Aircraft aircraft){
        Aircraft newAircraft = aircraftService.createAircraft(aircraft);

        if(newAircraft == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newAircraft);
    }

    @PutMapping("/updateby/id/{id}")
    public ResponseEntity<Aircraft> updateAircraft(@PathVariable long id, @RequestBody Aircraft aircraft) {
        Aircraft existingAircraft = aircraftService.getAircraftById(id);

        if(existingAircraft == null){
            return ResponseEntity.notFound().build();
        }
        Aircraft updatedAircraft = aircraftService.updateAircraft(id, aircraft);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedAircraft);
    }

    @DeleteMapping("/deleteby/id/{id}")
    public ResponseEntity<Aircraft> deleteAircraft(@PathVariable Long id) {
        Aircraft aircraftToDelete =  aircraftService.getAircraftById(id);

        if(aircraftToDelete == null){
            return ResponseEntity.notFound().build();
        }
        aircraftService.deleteAircraftById(id);
        return ResponseEntity.noContent().build();
    }

}
