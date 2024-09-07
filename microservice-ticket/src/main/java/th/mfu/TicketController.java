package th.mfu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import th.mfu.client.CustomerClient;
import th.mfu.client.MovieClient;
import th.mfu.client.RoundClient;
import th.mfu.client.SeatClient;
import th.mfu.client.TheatreClient;
import th.mfu.domain.Ticket;
import th.mfu.dto.CustomerDTO;
import th.mfu.dto.MovieDTO;
import th.mfu.dto.RoundDTO;
import th.mfu.dto.SeatDTO;
import th.mfu.dto.TheatreDTO;
import th.mfu.dto.TicketDTO;
import th.mfu.dto.mapper.TicketMapper;
import th.mfu.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private RoundClient roundClient;

    @Autowired
    private SeatClient seatClient;

    @Autowired
    private MovieClient movieClient;

    @Autowired
    private TheatreClient theatreClient;

    // POST - Create a new ticket
    @PostMapping
    @LoadBalanced
    public ResponseEntity<String> createTicket(@RequestBody TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticketMapper.updateTicketFromDto(ticketDTO, ticket);
        Long customerId = ticketDTO.getCustomerID();
        if (customerId == null) {
            return new ResponseEntity<>("Customer ID is required", HttpStatus.BAD_REQUEST);
        }
        ticketRepository.save(ticket);

        return new ResponseEntity<>("Ticket created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/alltickets")
    public ResponseEntity<List<Ticket>> getAllRounds() {
        List<Ticket> ticket = ticketRepository.findAll();
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    // GET - Retrieve a ticket by ID
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            TicketDTO ticketDTO = new TicketDTO();
            MovieDTO movieDTO = movieClient.getMovieById(ticket.get().getMovieID());
            CustomerDTO customerDTO = customerClient.getCustomerById(ticket.get().getCustomerID());
            TheatreDTO theatreDTO = theatreClient.getTheatreById(ticket.get().getTheatreID());
            SeatDTO seatDTO = seatClient.getSeatById(ticket.get().getSeatID());
            RoundDTO roundDTO = roundClient.getRoundById(ticket.get().getRoundID());
            ticketMapper.updateTicketFromEntity(ticket.get(), ticketDTO);
            ticketDTO.setCustomer(customerDTO);
            ticketDTO.setMovie(movieDTO);
            ticketDTO.setTheatre(theatreDTO);
            ticketDTO.setSeat(seatDTO);
            ticketDTO.setRound(roundDTO);
            return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT - Update an existing ticket
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTicket(@PathVariable Long id, @RequestBody TicketDTO ticketDTO) {
        Optional<Ticket> existingTicket = ticketRepository.findById(id);
        if (existingTicket.isPresent()) {
            Ticket ticket = existingTicket.get();
            ticketMapper.updateTicketFromDto(ticketDTO, ticket);
            ticketRepository.save(ticket);
            return new ResponseEntity<>("Ticket updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PATCH - Partially update a ticket
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchTicket(@PathVariable Long id, @RequestBody TicketDTO ticketDTO) {
        Optional<Ticket> existingTicket = ticketRepository.findById(id);
        if (existingTicket.isPresent()) {
            Ticket ticket = existingTicket.get();
            // Assuming patch updates only Customer, Round, or Seat
            CustomerDTO customerDTO = customerClient.getCustomerById(ticketDTO.getCustomer().getId());
            RoundDTO roundDTO = roundClient.getRoundById(ticketDTO.getRound().getId());
            SeatDTO seatDTO = seatClient.getSeatById(ticketDTO.getSeatID());

            ticketDTO.setCustomer(customerDTO);
            ticketDTO.setRound(roundDTO);
            ticketDTO.setSeat(seatDTO);

            ticketMapper.updateTicketFromDto(ticketDTO, ticket);
            ticketRepository.save(ticket);
            return new ResponseEntity<>("Ticket patched successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Delete a ticket by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            ticketRepository.deleteById(id);
            return new ResponseEntity<>("Ticket deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

// package th.mfu;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.apache.catalina.startup.ClassLoaderFactory.Repository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// import th.mfu.client.CustomerClient;
// import th.mfu.client.MovieClient;
// import th.mfu.domain.Ticket;
// import th.mfu.dto.CustomerDTO;
// import th.mfu.dto.MovieDTO;
// import th.mfu.dto.TicketDTO;
// import th.mfu.dto.mapper.TicketMapper;
// // import th.mfu.repository.MovieRepository;
// import th.mfu.repository.TicketRepository;

// @RestController
// public class TicketController {
// @Autowired
// TicketRepository ticketRepository;

// @Autowired
// CustomerClient customerClient;

// @Autowired
// TicketMapper ticketMapper;

// @Autowired
// MovieClient movieClient;

// @PostMapping("/ticket")
// public ResponseEntity<String> createTicket(@RequestBody TicketDTO dto) {
// Ticket ticket = new Ticket();
// // set other properties

// ticketMapper.updateTicketFromDto(dto, ticket);
// ticketRepository.save(ticket);
// return new ResponseEntity<String>("create success",HttpStatus.CREATED);
// }
// @GetMapping("/ticket/{id}")
// public ResponseEntity<TicketDTO> get(@PathVariable Long id){
// Optional<Ticket> ticket = ticketRepository.findById(id);
// CustomerDTO customerDTO =
// customerClient.getCustomerById(ticket.get().getId());
// MovieDTO movieDTO = movieClient.getMovieById(id);
// TicketDTO dto = new TicketDTO();

// ticketMapper.updateTicketFromEntity(ticket.get(), dto);
// dto.setCustomer(customerDTO);
// dto.setMovie(movieDTO);
// return new ResponseEntity<TicketDTO>(dto,HttpStatus.OK);
// }
// }
