package th.mfu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.Ticket;
import th.mfu.dto.TicketDTO;

public interface TicketRepository extends CrudRepository<Ticket,Long>{
    public List<Ticket> findAll();

}
