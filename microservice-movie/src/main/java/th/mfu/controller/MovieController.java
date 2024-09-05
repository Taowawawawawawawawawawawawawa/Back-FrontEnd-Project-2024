package th.mfu.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import th.mfu.domain.Movie;
import th.mfu.dto.MovieDTO;
import th.mfu.dto.mapper.MovieMapper;
import th.mfu.repository.MovieRepository;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    // POST - Create a new movie
    @PostMapping("/movies")
    public ResponseEntity<String> createMovie(@RequestBody MovieDTO dto) {
        Movie newMovie = new Movie();
        movieMapper.updateMovieFromDto(dto, newMovie);
        movieRepository.save(newMovie);
        return new ResponseEntity<>("Movie created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/movies/all")
    public ResponseEntity<Collection> getAllMovie() {
        return new ResponseEntity<Collection>(movieRepository.findAll(), HttpStatus.OK);
    }

    // GET - Get a movie by its ID
    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            MovieDTO dto = new MovieDTO();
            movieMapper.updateMovieFromEntity(movie.get(), dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT - Update an existing movie
    @PutMapping("/movies/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable Long id, @RequestBody MovieDTO dto) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            Movie existingMovie = movieOptional.get();
            movieMapper.updateMovieFromDto(dto, existingMovie);
            movieRepository.save(existingMovie);
            return new ResponseEntity<>("Movie updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }
    }

    // PATCH - Update certain fields of an existing movie
    @PatchMapping("/movies/{id}")
    public ResponseEntity<String> patchMovie(@PathVariable Long id, @RequestBody MovieDTO dto) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            Movie existingMovie = movieOptional.get();
            movieMapper.updateMovieFromDto(dto, existingMovie); // Only update fields present in the DTO
            movieRepository.save(existingMovie);
            return new ResponseEntity<>("Movie patched successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Delete a movie by its ID
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return new ResponseEntity<>("Movie deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }
    }
}
