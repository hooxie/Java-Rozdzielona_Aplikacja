package ms.movies.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.movies.dao.MovieRepository;
import ms.movies.model.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/webapi")
@RequiredArgsConstructor
public class MoviesRest {

    private final MovieRepository movieRepository;

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        log.info("about to get movies");
        return movieRepository.findAll();
    }

    @GetMapping("/movies/{id}")
    public Movie getMovie(@PathVariable int id) {
        log.info("about to retrieve movie {}", id);
        return movieRepository.findById(id).orElse(null);
    }

    @GetMapping("/directors/{id}/movies")
    public List<Movie> getMoviesByDirector(@PathVariable int id) {
        log.info("about to retrieve movies by director {}", id);
        return movieRepository.findMoviesByDirectorId(id);
    }

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);
        return movie;
    }
}
