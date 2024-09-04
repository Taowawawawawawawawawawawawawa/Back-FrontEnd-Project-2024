package th.mfu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.*;

public interface RoundRepository extends CrudRepository<Round, Long> {
    List<Round> findAll();
    List<Round> findByName(String name);
}
