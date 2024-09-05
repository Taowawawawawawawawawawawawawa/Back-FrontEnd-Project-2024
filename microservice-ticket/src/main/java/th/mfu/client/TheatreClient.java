package th.mfu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import th.mfu.dto.TheatreDTO;

@FeignClient(name = "cinemaTheatre",url = "http://localhost:8300")
public interface TheatreClient {
    @GetMapping("/theatre/{id}")
    TheatreDTO getTheatreById(@PathVariable("id") Long id);
}