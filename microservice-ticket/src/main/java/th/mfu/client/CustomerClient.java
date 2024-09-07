package th.mfu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import th.mfu.dto.CustomerDTO;

@FeignClient(name = "cinemaCustomer",url = "http://localhost:8200")
public interface CustomerClient {
    @GetMapping("/customers/{id}")
    CustomerDTO getCustomerById(@PathVariable("id") Long id);
}