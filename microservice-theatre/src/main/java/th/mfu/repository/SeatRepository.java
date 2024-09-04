package th.mfu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import th.mfu.domain.Seat;
import th.mfu.dto.SeatDTO;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<SeatDTO> findById(long id);
}
