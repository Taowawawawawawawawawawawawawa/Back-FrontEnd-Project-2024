package th.mfu.repository;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.Ticket;

public interface TicketRepository extends CrudRepository<Ticket,Long>{

}
