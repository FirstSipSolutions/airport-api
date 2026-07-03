package com.project.aircraft;

import com.project.passenger.Passenger;
import com.project.passenger.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {
    @Autowired
    private AircraftRepository aircraftRepository;
    private PassengerRepository passengerRepository;

    public AircraftService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<Aircraft> getAllAircraft(){

        return (List<Aircraft>) aircraftRepository.findAll();
    }

    public Aircraft getAircraftById(Long id) {
        Optional<Aircraft> existingAircraft = aircraftRepository.findById(id);

        if (existingAircraft.isPresent()) {

            return existingAircraft.get();
        }
        return null;
    }

    public Aircraft createAircraft(Aircraft aircraft){
        return aircraftRepository.save(aircraft);
    }

    public Aircraft updateAircraft(Long id,  Aircraft updateAircraft) {

    // added in for debug purpose
        System.out.println("Incoming airports: " + updateAircraft.getAirports());

        Optional<Aircraft> existingAircraft = aircraftRepository.findById(id);

        if (existingAircraft.isPresent()){
            Aircraft newAircraftData = existingAircraft.get();

            newAircraftData.setType(updateAircraft.getType());
            newAircraftData.setAirlineName(updateAircraft.getAirlineName());
            newAircraftData.setNumberOfPassengers(updateAircraft.getNumberOfPassengers());
            newAircraftData.setAirports(updateAircraft.getAirports());
            return aircraftRepository.save(newAircraftData);
        } else {
            return null;
        }
    }

    public void deleteAircraftById(Long id) {
        Optional<Aircraft> existingAircraft = aircraftRepository.findById(id);

        if (existingAircraft.isPresent()) {
            Aircraft aircraftToDelete = existingAircraft.get();
            List<Passenger> linkedPassengers = aircraftToDelete.getPassengers();

            for(Passenger passenger : linkedPassengers){
                passenger.getAircraft().remove(aircraftToDelete);
                passengerRepository.save(passenger);
            }
            aircraftRepository.deleteById(id);
        }
    }
}
