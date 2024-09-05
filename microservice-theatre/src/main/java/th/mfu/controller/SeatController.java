package th.mfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import th.mfu.domain.Seat;
import th.mfu.dto.SeatDTO;
import th.mfu.dto.mapper.SeatMapper;
import th.mfu.repository.SeatRepository;
import th.mfu.repository.TheatreRepository;

import java.util.List;
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
        Optional<Seat> seat = seatRepository.findById(id);
        if (seat.isPresent()) {
            SeatDTO seatDTO = new SeatDTO();
            seatMapper.updateSeatFromEntity(seat.get(), seatDTO);
            return new ResponseEntity<>(seatDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all seats in a theatre
    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<List<Seat>> getSeatsByTheatre(@PathVariable Long theatreId) {
        List<Seat> seats = seatRepository.findByTheatreId(theatreId);
        if (!seats.isEmpty()) {
            return new ResponseEntity<>(seats, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Create a new seat
    @PostMapping
    public ResponseEntity<String> createSeat(@RequestBody SeatDTO seatDTO) {
        Optional<Seat> existingSeat = seatRepository.findSeatByRowAndColumnAndTheatreId(
                seatDTO.getRow(), seatDTO.getColumn(), seatDTO.getTheatreId());
        if (existingSeat.isPresent()) {
            return new ResponseEntity<>("Seat already exists", HttpStatus.CONFLICT);
        }

        Seat newSeat = new Seat();
        seatMapper.updateSeatFromDto(seatDTO, newSeat);
        seatRepository.save(newSeat);
        return new ResponseEntity<>("Seat created successfully", HttpStatus.CREATED);
    }

    // Update seat details
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSeat(@PathVariable Long id, @RequestBody SeatDTO seatDTO) {
        Optional<Seat> seat = seatRepository.findById(id);
        if (seat.isPresent()) {
            Seat existingSeat = seat.get();
            seatMapper.updateSeatFromDto(seatDTO, existingSeat);
            seatRepository.save(existingSeat);
            return new ResponseEntity<>("Seat updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Patch seat availability
    @PatchMapping("/{id}/availability")
    public ResponseEntity<String> updateSeatAvailability(@PathVariable Long id, @RequestBody boolean available) {
        Optional<Seat> seat = seatRepository.findById(id);
        if (seat.isPresent()) {
            Seat existingSeat = seat.get();
            existingSeat.setAvailable(available);
            seatRepository.save(existingSeat);
            return new ResponseEntity<>("Seat availability updated", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a seat
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeat(@PathVariable Long id) {
        Optional<Seat> seat = seatRepository.findById(id);
        if (seat.isPresent()) {
            seatRepository.delete(seat.get());
            return new ResponseEntity<>("Seat deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
