package th.mfu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.domain.Ticket;
import th.mfu.dto.TicketDTO;
import th.mfu.dto.mapper.TicketMapper;
import th.mfu.repository.TicketRepository;

@RestController
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @PostMapping("/ticket")
    public ResponseEntity<String> createTicket(@RequestBody TicketDTO ticketDTO){
        Ticket newTicket = new Ticket();
        ticketMapper.updateTicketFromDto(ticketDTO,newTicket);
        ticketRepository.save(newTicket);
        return new ResponseEntity<String>("createSuccess",HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable Long id){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        TicketDTO dto = new TicketDTO();
        ticketMapper.updateTicketFromEntity(ticket.get(), dto); 
        return new ResponseEntity<TicketDTO>(dto,HttpStatus.OK);
    }
}
