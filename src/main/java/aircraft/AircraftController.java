package aircraft;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftController extends CrudRepository <Aircraft, Long> {
}
