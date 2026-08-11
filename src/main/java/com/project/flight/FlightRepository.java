package com.project.flight;

import com.project.airport.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>  {
    List<Flight> findByAirportDeparture(Airport airport);

    List<Flight> findByAirportArrival(Airport airport);

}
