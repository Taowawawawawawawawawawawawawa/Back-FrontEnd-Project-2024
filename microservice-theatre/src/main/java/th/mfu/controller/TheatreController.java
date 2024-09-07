
package th.mfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import th.mfu.domain.Theatre;
import th.mfu.domain.Seat;
import th.mfu.dto.TheatreDTO;
import th.mfu.dto.mapper.SeatMapper;
import th.mfu.dto.mapper.TheatreMapper;
import th.mfu.repository.TheatreRepository;
import th.mfu.repository.SeatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private TheatreMapper theatreMapper;

    // POST - Create a theatre and automatically create seats (A-F rows, 10 columns)@RequestBody TheatreDTO theatreDTO
    @PostMapping
    @Transactional
    public ResponseEntity<String> createTheatre() {
        try {
            // Create and save the theatre
            Theatre theatre = new Theatre();
            //theatre.setId(theatreDTO.getId());
            theatre = theatreRepository.save(theatre);

            // Automatically create seats (rows A-F, 10 seats per row)
            List<Seat> seats = generateSeatsForTheatre(theatre);
            seatRepository.saveAll(seats);

            return new ResponseEntity<>("Theatre and seats created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating theatre and seats", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Helper method to generate seats for a given theatre
    private List<Seat> generateSeatsForTheatre(Theatre theatre) {
        char[] rows = {'A', 'B', 'C', 'D', 'E', 'F'};
        int seatsPerRow = 10;
        List<Seat> seats = new ArrayList<>();

        for (char row : rows) {
            for (int number = 1; number <= seatsPerRow; number++) {
                Seat seat = new Seat();
                seat.setSeatRow(String.valueOf(row)); // Setting row as A, B, C, etc.
                seat.setSeatColumn(number); // Seat numbers from 1-10
                seat.setTheatre(theatre);  // Associate seat with the theatre
                seats.add(seat);
            }
        }
        return seats;
    }
    @GetMapping("/all")
    public ResponseEntity<List<TheatreDTO>> getAllTheatre() {
        List<Theatre> theatres = theatreRepository.findAll();
        List<TheatreDTO> theatreDTOs = theatres.stream()
                .map(theatreMapper::toTheatreDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(theatreDTOs, HttpStatus.OK);
    }
    // @PostMapping("/theatres")
    // public ResponseEntity<String> createTheatre() {
    // Theatre theatre = new Theatre();
    // theatreRepository.save(theatre); // Save theatre first to get its ID
    // List<Seat> seats = new ArrayList<>();
    // // Create seats A-F rows, 1-10 columns
    // for (char row = 'A'; row <= 'F'; row++) {
    // for (int col = 1; col <= 10; col++) {
    // Seat seat = new Seat();
    // String rowStr = String.valueOf(row);
    // seat.setSeatRow(rowStr);
    // seat.setSeatColumn(col);
    // seat.setAvailable(true);
    // seat.setVip(row == 'F'); // Row F is VIP
    // seat.setTheatre(theatre); // Link seat with theatre

    // seats.add(seat);
    // }
    // }

    // // Save all seats at once
    // seatRepository.saveAll(seats);

    // // Update theatre with the seats
    // theatre.setSeats(seats);
    // theatreRepository.save(theatre);

    // return new ResponseEntity<>("Theatre created with seats",
    // HttpStatus.CREATED);
    // }
    // Update theatre
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTheatre(@PathVariable Long id, @RequestBody TheatreDTO theatreDTO) {
        Optional<Theatre> theatreOpt = theatreRepository.findById(id);
        if (theatreOpt.isPresent()) {
            Theatre theatre = theatreOpt.get();
            theatreMapper.updateTheatreFromDto(theatreDTO, theatre); // Use mapper to update the theatre
            theatreRepository.save(theatre);
            return new ResponseEntity<>("Theatre updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a theatre
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTheatre(@PathVariable Long id) {
        Optional<Theatre> theatreOpt = theatreRepository.findById(id);
        if (theatreOpt.isPresent()) {
            theatreRepository.delete(theatreOpt.get());
            return new ResponseEntity<>("Theatre deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
