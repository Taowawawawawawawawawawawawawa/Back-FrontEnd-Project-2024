package th.mfu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import th.mfu.dto.SeatDTO;

@FeignClient(name = "cinemaSeat", url = "http://localhost:8300")  // Replace the URL with the actual seat service URL
public interface SeatClient {

    @GetMapping("/seats/{id}")
    SeatDTO getSeatById(@PathVariable("id") Long id);
}
