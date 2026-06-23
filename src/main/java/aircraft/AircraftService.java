package aircraft;


/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftService {
    @Autowired
    private AircraftRepository aircraftRepository;

    public List<Aircraft> getAllAircraft(){
        return (List<Aircraft>) aircraftRepository.findAll();
    }


}
