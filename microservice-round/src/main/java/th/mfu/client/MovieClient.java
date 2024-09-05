package th.mfu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import th.mfu.dto.MovieDTO;

@FeignClient(name = "cinemaMovie",url = "http://localhost:8200")
public interface MovieClient {
    @GetMapping("/movie/{id}")
    MovieDTO getMovieById(@PathVariable("id") Long id);
}