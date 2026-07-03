package com.project.aircraft;


/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AircraftRepository extends CrudRepository<Aircraft, Long> {
    List<Aircraft> findByAirportsId(Long id);
}
