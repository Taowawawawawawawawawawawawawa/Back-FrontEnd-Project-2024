package th.mfu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import th.mfu.domain.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findById(long id);
}
