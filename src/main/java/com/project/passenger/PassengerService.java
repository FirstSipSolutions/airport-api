package com.project.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public List<Passenger> getAllPassenger(){
        return (List<Passenger>) passengerRepository.findAll();
    }

    public Passenger getPassengerById(Long id) {
        Optional<Passenger> existingPassenger = passengerRepository.findById(id);

        if (existingPassenger.isPresent()) {

            return existingPassenger.get();
        }
        return null;
    }

    public Passenger createPassenger(Passenger passenger){
        return passengerRepository.save(passenger);
    }

    public Passenger updatePassenger(Long id,  Passenger updatePassenger) {
        Optional<Passenger> existingPassenger = passengerRepository.findById(id);

        if (existingPassenger.isPresent()){
            Passenger newPassengerData = existingPassenger.get();

            newPassengerData.setFirstName(updatePassenger.getFirstName());
            newPassengerData.setLastName(updatePassenger.getLastName());
            newPassengerData.setPhoneNumber(updatePassenger.getPhoneNumber());
            return passengerRepository.save(newPassengerData);
        } else {
            return null;
        }
    }

    public void deletePassengerById(Long id) {
        Optional<Passenger> existingPassenger = passengerRepository.findById(id);

        if (existingPassenger.isPresent())
            passengerRepository.deleteById(id);
    }
}
