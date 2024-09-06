package th.mfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.mfu.domain.Seat;
import th.mfu.domain.Theatre;
import th.mfu.dto.SeatDTO;
import th.mfu.dto.mapper.SeatMapper;
import th.mfu.repository.SeatRepository;
import th.mfu.repository.TheatreRepository;

import java.util.Optional;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private SeatMapper seatMapper;

    // Get seat by ID
    @GetMapping("/{id}")
    public ResponseEntity<SeatDTO> getSeatById(@PathVariable Long id) {
        Optional<Seat> seatOpt = seatRepository.findById(id);
        if (seatOpt.isPresent()) {
            SeatDTO seatDTO = seatMapper.toSeatDTO(seatOpt.get());
            return new ResponseEntity<>(seatDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Create a new seat
    @PostMapping
    public ResponseEntity<String> createSeat(@RequestBody SeatDTO seatDTO) {
        Optional<Theatre> theatreOpt = theatreRepository.findById(seatDTO.getTheatreId());
        if (theatreOpt.isPresent()) {
            Theatre theatre = theatreOpt.get();
            Seat seat = seatMapper.toSeat(seatDTO, theatre); // Use the mapper to map DTO to entity

            seatRepository.save(seat); // Save the seat
            return new ResponseEntity<>("Seat created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Theatre not found", HttpStatus.NOT_FOUND);
    }

    // Update seat details
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSeat(@PathVariable Long id, @RequestBody SeatDTO seatDTO) {
        Optional<Seat> seatOpt = seatRepository.findById(id);
        if (seatOpt.isPresent()) {
            Seat seat = seatOpt.get();
            seatMapper.updateSeatFromDto(seatDTO, seat); // Update the seat using the mapper
            seatRepository.save(seat);
            return new ResponseEntity<>("Seat updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a seat
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeat(@PathVariable Long id) {
        Optional<Seat> seatOpt = seatRepository.findById(id);
        if (seatOpt.isPresent()) {
            seatRepository.delete(seatOpt.get());
            return new ResponseEntity<>("Seat deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
