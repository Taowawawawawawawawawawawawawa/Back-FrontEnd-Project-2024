package th.mfu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import th.mfu.domain.Round;

public interface RoundRepository extends JpaRepository<Round, Long> {
    List<Round> findByTheatreID(Long theatreID);
    List<Round> findByMovieID(Long movieID);
}
