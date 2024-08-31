package th.mfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre,Long> {

} 


