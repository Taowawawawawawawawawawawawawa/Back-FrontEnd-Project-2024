package th.mfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.mfu.domain.Seat;
import th.mfu.domain.Theatre;
import th.mfu.dto.SeatDTO;
import th.mfu.dto.TheatreDTO;
import th.mfu.dto.mapper.SeatMapper;
import th.mfu.dto.mapper.TheatreMapper;
import th.mfu.repository.SeatRepository;
import th.mfu.repository.TheatreRepository;

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

    // Get theatre by ID
    @GetMapping("/{id}")
    public ResponseEntity<TheatreDTO> getTheatreById(@PathVariable Long id) {
        Optional<Theatre> theatreOpt = theatreRepository.findById(id);
        if (theatreOpt.isPresent()) {
            TheatreDTO theatreDTO = theatreMapper.toTheatreDTO(theatreOpt.get());
            // Map the seats to SeatDTOs
            List<SeatDTO> seatDTOs = theatreOpt.get().getSeats().stream()
                    .map(seatMapper::toSeatDTO)
                    .collect(Collectors.toList());
            theatreDTO.setSeats(seatDTOs);
            return new ResponseEntity<>(theatreDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

    // Create a new theatre
    @PostMapping
    public ResponseEntity<String> createTheatre() {
        // Step 1: Create a new Theatre
        Theatre theatre = new Theatre();
        Theatre savedTheatre = theatreRepository.save(theatre); // Save theatre and get the saved instance
    
        if (savedTheatre == null || savedTheatre.getId() == null) {
            return new ResponseEntity<>("Failed to create theatre", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
        // Step 2: Generate seats for the theatre (Rows A-F, 10 seats per row)
        char[] rows = {'A', 'B', 'C', 'D', 'E', 'F'};
        int numberOfColumns = 10;
    
        List<Seat> seats = new ArrayList<>();
    
        for (char row : rows) {
            for (int col = 1; col <= numberOfColumns; col++) {
                Seat seat = new Seat();
                seat.setSeatRow(String.valueOf(row));
                seat.setSeatColumn(col);
                seat.setTheatre(savedTheatre);  // Associate seat with the saved theatre
                seat.setTheatreId(savedTheatre.getId());
                seats.add(seat);  // Add to the list of seats
            }
        }
    
        // Step 3: Save all seats at once
        seatRepository.saveAll(seats);
    
        return new ResponseEntity<>("Theatre and seats created successfully", HttpStatus.CREATED);
    }

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
