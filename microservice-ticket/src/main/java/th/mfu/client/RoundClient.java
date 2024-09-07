package th.mfu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import th.mfu.dto.RoundDTO;

@FeignClient(name = "microservice-round")
public interface RoundClient {
    @GetMapping("/rounds/{id}")
    RoundDTO getRoundById(@PathVariable("id") Long id);
}
