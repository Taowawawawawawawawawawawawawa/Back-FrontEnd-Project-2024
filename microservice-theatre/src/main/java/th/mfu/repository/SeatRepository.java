package th.mfu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import th.mfu.domain.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    // Find all seats by theatre ID
    List<Seat> findByTheatreId(Long theatreId);

    // Find seat by row, column, and theatre ID (used to check if the seat already exists)
    Optional<Seat> findSeatByRowAndColumnAndTheatreId(char row, int column, Long theatreId);
}
