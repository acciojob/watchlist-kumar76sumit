package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    private HashMap<String,Movie> movieList;
    private HashMap<String,Director> directorList;
    private HashMap<Director,List<Movie>> directorMovieMap;

    public MovieRepository() {
        movieList=new HashMap<>();
        directorList=new HashMap<>();
        directorMovieMap=new HashMap<>();
    }

    public ResponseEntity<String> addMovie(@RequestBody Movie movie)
    {
        movieList.put(movie.getName(),movie);
        return new ResponseEntity<>("New movie added", HttpStatus.CREATED);
    }
    public ResponseEntity<String> addDirector(@RequestBody Director director)
    {
        directorList.put(director.getName(),director);
        return new ResponseEntity<>("New director added", HttpStatus.CREATED);
    }
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName)
    {
        if(movieList.containsKey(movieName) && directorList.containsKey(directorName))
        {
            Movie movie=movieList.get(movieName);
            Director director=directorList.get(directorName);
            directorMovieMap.putIfAbsent(director,new ArrayList<Movie>());
            List<Movie> movies=directorMovieMap.get(director);
            movies.add(movie);
            directorMovieMap.put(director,movies);
        }
        return new ResponseEntity<>("Director and Movie got paired", HttpStatus.CREATED);
    }
    public ResponseEntity<Movie> getMovieByName(@PathVariable("movieName") String movieName)
    {
        Movie movie=movieList.get(movieName);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }
    public ResponseEntity<Director> getDirectorByName(@PathVariable("directorName") String directorName)
    {
        Director director=directorList.get(directorName);
        return new ResponseEntity<>(director, HttpStatus.CREATED);
    }
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("directorName") String directorName)
    {
        Director director=directorList.get(directorName);
        List<String> movies=new ArrayList<>();
        for(Movie movie:directorMovieMap.get(director))
        {
            movies.add(movie.getName());
        }
        return new ResponseEntity<>(movies, HttpStatus.CREATED);
    }
    public ResponseEntity<List<String>> findAllMovies()
    {
        List<String> movies=new ArrayList<>();
        movies.addAll(movieList.keySet());
        return new ResponseEntity<>(movies, HttpStatus.CREATED);
    }
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("directorName") String directorName)
    {
        Director director=directorList.get(directorName);
        for(Movie movie:directorMovieMap.get(director))
        {
            movieList.remove(movie.getName());
        }
        directorMovieMap.remove(director);
        directorList.remove(directorName);
        return new ResponseEntity<>("Deleted the Doctor", HttpStatus.CREATED);
    }
    public ResponseEntity<String> deleteAllDirectors()
    {
        for(String directorName:directorList.keySet())
        {
            Director director=directorList.get(directorName);
            for(Movie movie:directorMovieMap.get(director))
            {
                movieList.remove(movie.getName());
            }
            directorMovieMap.remove(director);
            directorList.remove(directorName);
        }
        return new ResponseEntity<>("All Doctors and their movies deleted", HttpStatus.CREATED);
    }
}
