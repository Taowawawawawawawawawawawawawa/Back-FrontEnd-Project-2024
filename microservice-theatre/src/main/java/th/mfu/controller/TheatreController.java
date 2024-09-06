package th.mfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import th.mfu.domain.Seat;
import th.mfu.domain.Theatre;
import th.mfu.dto.SeatDTO;
import th.mfu.dto.TheatreDTO;
import th.mfu.dto.mapper.SeatMapper;
import th.mfu.dto.mapper.TheatreMapper;
import th.mfu.repository.SeatRepository;
import th.mfu.repository.TheatreRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TheatreController {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private TheatreMapper theatreMapper;

    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private RestTemplate restTemplate; // For making HTTP requests to SeatController

    @PostMapping("/theatres")
    public ResponseEntity<String> createTheatre() {
        Theatre theatre = new Theatre();
        theatreRepository.save(theatre); // Save theatre first to get its ID
        List<SeatDTO> seats = new ArrayList<>();
        // Create seats A-F rows, 1-10 columns
        for (char row = 'A'; row <= 'F'; row++) {
            for (int col = 1; col <= 10; col++) {
                SeatDTO seatDTO = new SeatDTO();
                String rowStr = String.valueOf(row);
                seatDTO.setSeatRow(rowStr);
                seatDTO.setSeatColumn(col);
                seatDTO.setAvailable(true);
                seatDTO.setVip(row == 'F'); // Row F is VIP
                seatDTO.setTheatreId(theatre.getId());
                seats.add(seatDTO);

                // Post request to SeatController
                ResponseEntity<String> response = restTemplate.postForEntity(
                        "http://localhost:8300/seats", // Adjust URL if needed
                        seatDTO,
                        String.class);

                if (response.getStatusCode() != HttpStatus.CREATED) {
                    return new ResponseEntity<>("Error creating seats", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }

        return new ResponseEntity<>("Theatre created with seats", HttpStatus.CREATED);
    }

    @GetMapping("/theatres/{id}")
    public ResponseEntity<TheatreDTO> getTheatre(@PathVariable Long id) {
        Optional<Theatre> theatre = theatreRepository.findById(id);
        if (theatre.isPresent()) {
            TheatreDTO theatreDTO = new TheatreDTO();
            theatreMapper.updateTheatreFromEntity(theatre.get(), theatreDTO);
            theatreDTO.setSeats(theatre.get().getSeats().stream()
                    .map(seat -> {
                        SeatDTO seatDTO = new SeatDTO();
                        seatMapper.updateSeatFromEntity(seat, seatDTO);
                        return seatDTO;
                    })
                    .collect(Collectors.toList()));
            return new ResponseEntity<>(theatreDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/theatres/all")
    public ResponseEntity<List<TheatreDTO>> getAllTheatre() {
        List<Theatre> theatres = theatreRepository.findAll();
        List<TheatreDTO> theatreDTOs = theatres.stream()
                .map(theatreMapper::toTheatreDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(theatreDTOs, HttpStatus.OK);
    }

    @PutMapping("/theatres/{id}")
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

    @PatchMapping("/theatres/{id}")
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

    @DeleteMapping("/theatres/{id}")
    public ResponseEntity<String> deleteTheatre(@PathVariable Long id) {
        Optional<Theatre> theatre = theatreRepository.findById(id);
        if (theatre.isPresent()) {
            theatreRepository.delete(theatre.get());
            return new ResponseEntity<>("Theatre deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
