package th.mfu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import th.mfu.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Long>{
    public List<Ticket> findAll();

}
