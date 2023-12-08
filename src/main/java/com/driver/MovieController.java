package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie)
    {
        return movieService.addMovie(movie);
    }
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director)
    {
        return movieService.addDirector(director);
    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movieName") String movieName,@RequestParam("directorName") String directorName)
    {
        return movieService.addMovieDirectorPair(movieName,directorName);
    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("movieName") String movieName)
    {
        return movieService.getMovieByName(movieName);
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("directorName") String directorName)
    {
        return movieService.getDirectorByName(directorName);
    }
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<Movie>> getMoviesByDirectorName(@PathVariable("directorName") String directorName)
    {
        return movieService.getMoviesByDirectorName(directorName);
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<Movie>> findAllMovies()
    {
        return movieService.findAllMovies();
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("directorName") String directorName)
    {
        return movieService.deleteDirectorByName(directorName);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors()
    {
        return movieService.deleteAllDirectors();
    }
}

