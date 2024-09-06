package th.mfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import th.mfu.domain.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre,Long> {
    @Query("SELECT t FROM Theatre t LEFT JOIN FETCH t.seats WHERE t.id = :id")
    Theatre findByIdWithSeats(@Param("id") Long id);

    Theatre findByTheatreId(Long id);
} 


