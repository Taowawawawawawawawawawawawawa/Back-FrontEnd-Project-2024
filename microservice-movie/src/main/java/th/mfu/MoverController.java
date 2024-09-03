package th.mfu;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.domain.Movie;
import th.mfu.dto.MovieDTO;
import th.mfu.dto.mapper.MovieMapper;
import th.mfu.repository.MovieRepository;

@RestController
public class MoverController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;



    @PostMapping("/movie")
    public ResponseEntity<String> createCustomer(@RequestBody MovieDTO dto){
        Movie newMovie = new Movie();
        movieMapper.updateMovieFromDto(dto, newMovie);
        movieRepository.save(newMovie);
        return new ResponseEntity<String>("create success",HttpStatus.CREATED);
    }
    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieDTO> getCustomer(@PathVariable Long id){
        Optional<Movie> Movie = movieRepository.findById(id);
        MovieDTO dto = new MovieDTO();
        movieMapper.updateMovieFromEntity(Movie.get(), dto);
        return new ResponseEntity<MovieDTO>(dto,HttpStatus.OK);
    }
}
