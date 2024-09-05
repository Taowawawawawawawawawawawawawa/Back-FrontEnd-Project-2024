package th.mfu.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.client.CustomerClient;
import th.mfu.client.RoundClient;
import th.mfu.domain.Ticket;
import th.mfu.dto.CustomerDTO;
import th.mfu.dto.RoundDTO;
import th.mfu.dto.TicketDTO;
import th.mfu.dto.mapper.TicketMapper;
import th.mfu.repository.TicketRepository;

@RestController
public class TicketController {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    CustomerClient customerClient;

    @Autowired
    RoundClient roundClient;

    @Autowired
    TicketMapper ticketMapper;

    @PostMapping("/ticket")
    public ResponseEntity<String> createTicket(@RequestBody TicketDTO dto) {
        Ticket ticket = new Ticket();
       
        ticketMapper.updateTicketFromDto(dto, ticket);
        ticketRepository.save(ticket);
        return new ResponseEntity<String>("create success",HttpStatus.CREATED);
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable Long id){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        CustomerDTO customerDTO = customerClient.getCustomerById(ticket.get().getCustomerID());
        RoundDTO roundDTO = roundClient.getRoundById(ticket.get().getRoundID());

        TicketDTO dto = new TicketDTO();
        ticketMapper.updateTicketFromEntity(ticket.get(), dto);
        
        dto.setCustomer(customerDTO);
        dto.setRound(roundDTO);
        return new ResponseEntity<TicketDTO>(dto,HttpStatus.OK);
    }
}