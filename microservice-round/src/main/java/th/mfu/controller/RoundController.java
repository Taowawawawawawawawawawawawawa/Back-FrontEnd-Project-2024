package th.mfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import th.mfu.client.MovieClient;
import th.mfu.client.TheatreClient;
import th.mfu.domain.Round;
import th.mfu.dto.MovieDTO;
import th.mfu.dto.RoundDTO;
import th.mfu.dto.TheatreDTO;
import th.mfu.dto.mapper.RoundMapper;
import th.mfu.repository.RoundRepository;

import java.util.Optional;

@RestController
@RequestMapping("/rounds")
public class RoundController {

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private RoundMapper roundMapper;

    @Autowired
    private MovieClient movieClient;

    @Autowired
    private TheatreClient theatreClient;

    // POST - Create a new round
    @PostMapping
    public ResponseEntity<String> createRound(@RequestBody RoundDTO roundDTO) {
        // Retrieve Movie and Theatre from their respective microservices
        MovieDTO movieDTO = movieClient.getMovieById(roundDTO.getMovie().getId());
        TheatreDTO theatreDTO = theatreClient.getTheatreById(roundDTO.getTheatre().getId());

        // Set the retrieved data in the RoundDTO
        roundDTO.setMovie(movieDTO);
        roundDTO.setTheatre(theatreDTO);

        // Map DTO to Entity and save the round
        Round round = new Round();
        roundMapper.updateRoundFromDto(roundDTO, round);
        roundRepository.save(round);

        return new ResponseEntity<>("Round created successfully", HttpStatus.CREATED);
    }

    // GET - Retrieve a round by ID
    @GetMapping("/{id}")
    public ResponseEntity<RoundDTO> getRound(@PathVariable Long id) {
        Optional<Round> round = roundRepository.findById(id);
        if (round.isPresent()) {
            RoundDTO roundDTO = new RoundDTO();
            roundMapper.updateRoundFromEntity(round.get(), roundDTO);
            return new ResponseEntity<>(roundDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT - Update an existing round
    @PutMapping("/{id}")
    public ResponseEntity<String> updateRound(@PathVariable Long id, @RequestBody RoundDTO roundDTO) {
        Optional<Round> existingRound = roundRepository.findById(id);
        if (existingRound.isPresent()) {
            Round round = existingRound.get();
            roundMapper.updateRoundFromDto(roundDTO, round);
            roundRepository.save(round);
            return new ResponseEntity<>("Round updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PATCH - Partially update a round
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchRound(@PathVariable Long id, @RequestBody RoundDTO roundDTO) {
        Optional<Round> existingRound = roundRepository.findById(id);
        if (existingRound.isPresent()) {
            Round round = existingRound.get();
            // Assuming patch is just updating the movie and theatre
            MovieDTO movieDTO = movieClient.getMovieById(roundDTO.getMovie().getId());
            TheatreDTO theatreDTO = theatreClient.getTheatreById(roundDTO.getTheatre().getId());

            roundDTO.setMovie(movieDTO);
            roundDTO.setTheatre(theatreDTO);

            roundMapper.updateRoundFromDto(roundDTO, round);
            roundRepository.save(round);
            return new ResponseEntity<>("Round patched successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Delete a round by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRound(@PathVariable Long id) {
        Optional<Round> round = roundRepository.findById(id);
        if (round.isPresent()) {
            roundRepository.deleteById(id);
            return new ResponseEntity<>("Round deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
