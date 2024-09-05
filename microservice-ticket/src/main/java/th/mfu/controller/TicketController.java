package th.mfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import th.mfu.client.CustomerClient;
import th.mfu.client.RoundClient;
import th.mfu.client.SeatClient;
import th.mfu.domain.Ticket;
import th.mfu.dto.CustomerDTO;
import th.mfu.dto.RoundDTO;
import th.mfu.dto.SeatDTO;
import th.mfu.dto.TicketDTO;
import th.mfu.dto.mapper.TicketMapper;
import th.mfu.repository.TicketRepository;

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

    // POST - Create a new ticket
    @PostMapping
    public ResponseEntity<String> createTicket(@RequestBody TicketDTO ticketDTO) {
        // Retrieve Customer, Round, and Seat data using Feign Clients
        CustomerDTO customerDTO = customerClient.getCustomerById(ticketDTO.getCustomer().getId());
        RoundDTO roundDTO = roundClient.getRoundById(ticketDTO.getRound().getId());
        SeatDTO seatDTO = seatClient.getSeatById(ticketDTO.getSeat().getId());

        // Set the retrieved data in the TicketDTO
        ticketDTO.setCustomer(customerDTO);
        ticketDTO.setRound(roundDTO);
        ticketDTO.setSeat(seatDTO);

        // Map DTO to Entity and save the ticket
        Ticket ticket = new Ticket();
        ticketMapper.updateTicketFromDto(ticketDTO, ticket);
        ticketRepository.save(ticket);

        return new ResponseEntity<>("Ticket created successfully", HttpStatus.CREATED);
    }

    // GET - Retrieve a ticket by ID
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            TicketDTO ticketDTO = new TicketDTO();
            ticketMapper.updateTicketFromEntity(ticket.get(), ticketDTO);
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
            SeatDTO seatDTO = seatClient.getSeatById(ticketDTO.getSeat().getId());

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
