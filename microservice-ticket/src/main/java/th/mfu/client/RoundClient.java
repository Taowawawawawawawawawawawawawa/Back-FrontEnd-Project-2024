package th.mfu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import th.mfu.dto.RoundDTO;

@FeignClient(name = "cinemaRound",url = "http://localhost:8200")
public interface RoundClient {
    @GetMapping("/round/{id}")
    RoundDTO getRoundById(@PathVariable("id") Long id);
}