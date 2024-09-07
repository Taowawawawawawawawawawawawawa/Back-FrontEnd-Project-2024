
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
import org.springframework.web.client.RestClientException;

import java.util.Optional;
import java.util.List;

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
    private TheatreClient theatreClient; // Fetch theatre and seat data

    // POST - Create a new round
    @PostMapping
    public ResponseEntity<String> createRound(@RequestBody RoundDTO roundDTO) {
        // Fetch theatre and seat details from TheatreClient
        TheatreDTO theatreDTO = theatreClient.getTheatreById(roundDTO.getTheatreID());
        roundDTO.setTheatre(theatreDTO);

        // Save the round with theatreID and movieID
        Round round = new Round();
        roundMapper.updateRoundFromDto(roundDTO, round);
        roundRepository.save(round);

        return new ResponseEntity<>("Round created successfully", HttpStatus.CREATED);
    }

    // GET - Retrieve a round by ID and include theatre details
    @GetMapping("/{id}")
    public ResponseEntity<RoundDTO> getRound(@PathVariable Long id) {
        Optional<Round> round = roundRepository.findById(id);
        if (round.isPresent()) {
            RoundDTO roundDTO = new RoundDTO();
            roundMapper.updateRoundFromEntity(round.get(), roundDTO);

            // Fetch theatre and seat details
            TheatreDTO theatreDTO = theatreClient.getTheatreById(roundDTO.getTheatreID());
            roundDTO.setTheatre(theatreDTO);

            return new ResponseEntity<>(roundDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allrounds")
    public ResponseEntity<List<Round>> getAllRounds() {
        List<Round> rounds = roundRepository.findAll();
        return new ResponseEntity<>(rounds, HttpStatus.OK);
    }
    

    // Additional controller methods for updating, deleting, etc.
}
