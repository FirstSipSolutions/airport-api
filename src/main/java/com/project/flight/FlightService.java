package com.project.flight;

import com.project.airport.Airport;
import com.project.airport.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight getFlightById(Long id){
        Optional<Flight> existingFlight = flightRepository.findById(id);

        if (existingFlight.isPresent()) {
            return existingFlight.get();
        }
        return null;
    }

    public Flight updateFlight(Long id, Flight updateFlight) {
        Optional<Flight> existingFlight = flightRepository.findById(id);

        if (existingFlight.isPresent()){
            Flight newFlightData = existingFlight.get();

            newFlightData.setStatus(updateFlight.getStatus());
            newFlightData.setTerminal(updateFlight.getTerminal());
            newFlightData.setFlightNumber(updateFlight.getFlightNumber());
            newFlightData.setDeparture(updateFlight.getDeparture());
            newFlightData.setArrival(updateFlight.getArrival());
            return flightRepository.save(newFlightData);
        } else {
            return null;
        }
    }

    public void deleteFlight(Long id) {
        Optional<Flight> existingFlight = flightRepository.findById(id);

        if (existingFlight.isPresent()){
            flightRepository.deleteById(id);
        }

    }

    public List<Flight> getDepartures(Long airportId){
        Airport airportDepartures = airportRepository.findById(airportId).orElse(null);

        if (airportDepartures == null){
            System.out.println("Airport not found");
            return null;
        }

        return flightRepository.findByAirportDeparture(airportDepartures);
    }

    public List<Flight> getArrivals(Long airportId) {
        Airport airportArrivals = airportRepository.findById(airportId).orElse(null);

        if (airportArrivals == null){
            System.out.println("Airport not found");
            return null;
        }

        return flightRepository.findByAirportArrival(airportArrivals);
    }
}
