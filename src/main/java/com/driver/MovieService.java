package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public ResponseEntity<String> addMovie(@RequestBody Movie movie)
    {
        return movieRepository.addMovie(movie);
    }
    public ResponseEntity<String> addDirector(@RequestBody Director director)
    {
        return movieRepository.addDirector(director);
    }
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName)
    {
        return movieRepository.addMovieDirectorPair(movieName,directorName);
    }
    public ResponseEntity<Movie> getMovieByName(@PathVariable("movieName") String movieName)
    {
        return movieRepository.getMovieByName(movieName);
    }
    public ResponseEntity<Director> getDirectorByName(@PathVariable("directorName") String directorName)
    {
        return movieRepository.getDirectorByName(directorName);
    }
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("directorName") String directorName)
    {
        return movieRepository.getMoviesByDirectorName(directorName);
    }
    public ResponseEntity<List<String>> findAllMovies()
    {
        return movieRepository.findAllMovies();
    }
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("directorName") String directorName)
    {
        return movieRepository.deleteDirectorByName(directorName);
    }
    public ResponseEntity<String> deleteAllDirectors()
    {
        return movieRepository.deleteAllDirectors();
    }
}
