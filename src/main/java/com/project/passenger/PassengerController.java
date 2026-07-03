package com.project.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

@RestController
@RequestMapping("/api/passengers")
@CrossOrigin
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public ResponseEntity<Page<Passenger>> getAllPassengers(Pageable pageable) {
        Page<Passenger> passenger = passengerService.getAllPassengers(pageable);
        if (passenger.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(passenger);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable long id) {
        Passenger passengerToReturn = passengerService.getPassengerById(id);
        if(passengerToReturn == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(passengerToReturn);
    }

    @PostMapping
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger){
        Passenger newPassenger = passengerService.createPassenger(passenger);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPassenger);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable long id, @RequestBody Passenger passenger) {
        Passenger existingPassenger = passengerService.getPassengerById(id);
        if(existingPassenger == null){
            return ResponseEntity.notFound().build();
        }
        Passenger updatedPassenger = passengerService.updatePassenger(id, passenger);
        return ResponseEntity.ok(updatedPassenger);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Passenger> deletePassenger(@PathVariable long id) {
        Passenger passengerToDelete =  passengerService.getPassengerById(id);
        if(passengerToDelete == null){
            return ResponseEntity.notFound().build();
        }
        passengerService.deletePassengerById(id);
        return ResponseEntity.noContent().build();
    }
}
