package ms.cinemas.dao;

import ms.cinemas.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Integer> {

    List<Cinema> findCinemasByMoviesIsContaining(int id);
}
