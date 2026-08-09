package com.project.aircraft;


/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    List<Aircraft> findByAirportsId(Long id);
}
