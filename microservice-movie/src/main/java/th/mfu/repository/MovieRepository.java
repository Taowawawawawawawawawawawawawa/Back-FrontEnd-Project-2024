package th.mfu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import th.mfu.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
