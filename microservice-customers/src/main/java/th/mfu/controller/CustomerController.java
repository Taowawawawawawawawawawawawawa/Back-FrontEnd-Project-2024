package th.mfu.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @PostMapping("/customers")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO dto) {
        Customer newCustomer = new Customer();

        // Don't set the id manually; Hibernate will handle it
        customerMapper.updateCustomerFromDto(dto, newCustomer);

        if (customerRepository.findByPhoneNum(dto.getPhoneNum()) == null) {
            customerRepository.save(newCustomer); // Hibernate will generate the id automatically
            return new ResponseEntity<>("Customer created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("This phone number is already registered.", HttpStatus.CONFLICT);
        }
    }

    // Login
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> login(@RequestBody CustomerDTO dto) {
        Customer customer = customerRepository.findByPhoneNum(dto.getPhoneNum());
        if (customer != null && customer.getPass().equals(dto.getPass())) {
            // Return a simple token (for simplicity, you can add JWT later)
            String token = "dummy-token-for-" + dto.getPhoneNum(); // Replace with actual token generation
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }

    // Get profile data
    @GetMapping("/profile")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<CustomerDTO> getProfile(@RequestHeader("Authorization") String token) {
        // Simulate token validation (in the future, replace this with proper JWT
        // validation)
        if (token == null || !token.startsWith("Bearer dummy-token-for")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Extract phone number from the token and fetch the customer
        String phoneNum = token.replace("Bearer dummy-token-for-", "").trim();
        Customer customer = customerRepository.findByPhoneNum(phoneNum);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        CustomerDTO dto = new CustomerDTO();
        dto.setName(customer.getName());
        dto.setPhoneNum(customer.getPhoneNum());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // GET: Get a customer by ID
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        if (!customerRepository.existsById(id)) {
            return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
        }
        Optional<Customer> customer = customerRepository.findById(id);
        CustomerDTO dto = new CustomerDTO();
        customerMapper.updateCustomerFromEntity(customer.get(), dto);
        return new ResponseEntity<CustomerDTO>(dto, HttpStatus.OK);
    }

    // GET: Get a customer by ID
    @GetMapping("/customers/all")
    public ResponseEntity<Collection> getAllCustomer() {
        return new ResponseEntity<Collection>(customerRepository.findAll(), HttpStatus.OK);
    }

    // PUT: Update a customer by ID (full update)
    @PutMapping("/customers/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isPresent()) {
            Customer existingCustomer = customerOpt.get();
            customerMapper.updateCustomerFromDto(dto, existingCustomer);
            if (customerRepository.findByPhoneNum(dto.getPhoneNum()) == null) {
                customerRepository.save(existingCustomer);
                return new ResponseEntity<String>("Customer updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("This phone number is already registered.", HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<String>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }

    // PATCH: Partially update a customer by ID
    @PatchMapping("/customers/{id}")
    public ResponseEntity<String> patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isPresent()) {
            Customer existingCustomer = customerOpt.get();
            if (dto.getName() != null) {
                existingCustomer.setName(dto.getName());
            }
            if (dto.getAge() != 0) {
                existingCustomer.setAge(dto.getAge());
            }
            if (dto.getPhoneNum() != null) {
                if (customerRepository.findByPhoneNum(dto.getPhoneNum()) != null) {
                    return new ResponseEntity<String>("This phone number is already registered.", HttpStatus.CONFLICT);
                }
                existingCustomer.setPhoneNum(dto.getPhoneNum());
            }
            if (dto.getPass() != null) {
                existingCustomer.setPass(dto.getPass());
            }
            customerRepository.save(existingCustomer);
            return new ResponseEntity<>("Customer patched successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }

    // DELETE: Delete a customer by ID
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            System.out.println("Customer with ID " + id + " deleted successfully.");
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
        } else {
            System.out.println("Customer with ID " + id + " not found.");
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }

}
