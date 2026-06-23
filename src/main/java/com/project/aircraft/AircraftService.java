package com.project.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {
    @Autowired
    private AircraftRepository aircraftRepository;

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
        Optional<Aircraft> existingAircraft = aircraftRepository.findById(id);

        if (existingAircraft.isPresent()){
            Aircraft newAircraftData = existingAircraft.get();

            newAircraftData.setType(updateAircraft.getType());
            newAircraftData.setAirlineName(updateAircraft.getAirlineName());
            newAircraftData.setNumberOfPassengers(updateAircraft.getNumberOfPassengers());
            return aircraftRepository.save(newAircraftData);
        } else {
            return null;
        }
    }

    public void deleteAircraftById(Long id) {
        Optional<Aircraft> existingAircraft = aircraftRepository.findById(id);

        if (existingAircraft.isPresent())
            aircraftRepository.deleteById(id);
    }

}
