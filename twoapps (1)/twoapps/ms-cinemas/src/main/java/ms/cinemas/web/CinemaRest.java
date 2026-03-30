package ms.cinemas.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.cinemas.dao.CinemaRepository;
import ms.cinemas.model.Cinema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/webapi")
@RequiredArgsConstructor
public class CinemaRest {

    private final CinemaRepository cinemaRepository;
    @Value("${ms.movie.service.url}")
    private String movieServiceUrl;

    @GetMapping("/cinemas")
    public List<Cinema> getCinemas() {
        log.info("about to retrieve all cinemas");
        List<Cinema> cinemas = cinemaRepository.findAll();
        cinemas.forEach(this::fillMovieNames);
        return cinemas;
    }

    @GetMapping("/movies/{id}/cinemas")
    public List<Cinema> getCinemasByMovie(@PathVariable int id) {
        log.info("about to retrieve cinemas by movie {}", id);
        List<Cinema> cinemas = cinemaRepository.findCinemasByMoviesIsContaining(id);
        cinemas.forEach(this::fillMovieNames);
        return cinemas;
    }

    private void fillMovieNames(Cinema cinema) {
        cinema.getMovies().forEach(movieId -> {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<MovieDTO> responseEntity = restTemplate.exchange(
                    movieServiceUrl + "/movies/" + movieId,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    MovieDTO.class
            );
            if (responseEntity.getBody() != null) {
                String movieName = responseEntity.getBody().getTitle();
                cinema.getMovieNames().add(movieName);
            }
        });
    }

    @Data
    static class MovieDTO {
        private int id;
        private String title;
        private String poster;
        private float rating;
    }
}