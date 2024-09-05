package th.mfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import th.mfu.domain.Seat;
import th.mfu.domain.Theatre;
import th.mfu.dto.SeatDTO;
import th.mfu.dto.TheatreDTO;
import th.mfu.dto.mapper.TheatreMapper;
import th.mfu.repository.SeatRepository;
import th.mfu.repository.TheatreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private TheatreMapper theatreMapper;

    @PostMapping
    public ResponseEntity<String> createTheatre() {
        Theatre theatre = new Theatre();
        List<Seat> seats = new ArrayList<>();

        // Create seats A-F rows, 1-10 columns
        for (char row = 'A'; row <= 'F'; row++) {
            for (int col = 1; col <= 10; col++) {
                Seat seat = new Seat();
                seat.setRow(row);
                seat.setColumn(col);
                seat.setAvailable(true);
                if (row == 'F') {
                    seat.setVip(true); // Row F is VIP
                } else {
                    seat.setVip(false);
                }
                seat.setTheatre(theatre);
                seats.add(seat);
            }
        }
        theatre.setSeats(seats);
        theatreRepository.save(theatre); // Cascade will save seats as well
        return new ResponseEntity<>("Theatre created with seats", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheatreDTO> getTheatre(@PathVariable Long id) {
        Optional<Theatre> theatre = theatreRepository.findById(id);
        if (theatre.isPresent()) {
            TheatreDTO theatreDTO = new TheatreDTO();
            theatreMapper.updateTheatreFromEntity(theatre.get(), theatreDTO);
            return new ResponseEntity<>(theatreDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTheatre(@PathVariable Long id, @RequestBody TheatreDTO theatreDTO) {
        Optional<Theatre> theatre = theatreRepository.findById(id);
        if (theatre.isPresent()) {
            Theatre existingTheatre = theatre.get();
            theatreMapper.updateTheatreFromDto(theatreDTO, existingTheatre);
            theatreRepository.save(existingTheatre);
            return new ResponseEntity<>("Theatre updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdateTheatre(@PathVariable Long id, @RequestBody TheatreDTO theatreDTO) {
        Optional<Theatre> theatre = theatreRepository.findById(id);
        if (theatre.isPresent()) {
            Theatre existingTheatre = theatre.get();
            theatreMapper.updateTheatreFromDto(theatreDTO, existingTheatre);
            theatreRepository.save(existingTheatre);
            return new ResponseEntity<>("Theatre partially updated", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTheatre(@PathVariable Long id) {
        Optional<Theatre> theatre = theatreRepository.findById(id);
        if (theatre.isPresent()) {
            theatreRepository.delete(theatre.get());
            return new ResponseEntity<>("Theatre deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
