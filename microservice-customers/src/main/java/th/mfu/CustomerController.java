package th.mfu;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.domain.Customer;
import th.mfu.dto.CustomerDTO;
import th.mfu.dto.mapper.CustomerMapper;
import th.mfu.repository.CustomerRespository;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRespository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;



    @PostMapping("/customer")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO dto){
        Customer newCustomer = new Customer();
        customerMapper.updateCustomerFromDto(dto, newCustomer);
        customerRepository.save(newCustomer);
        return new ResponseEntity<String>("create success",HttpStatus.CREATED);
    }
    
    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        CustomerDTO dto = new CustomerDTO();
        customerMapper.updateCustomerFromEntity(customer.get(), dto);
        return new ResponseEntity<CustomerDTO>(dto,HttpStatus.OK);
    }
}
