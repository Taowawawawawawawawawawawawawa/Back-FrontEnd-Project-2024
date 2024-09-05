package th.mfu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import th.mfu.domain.Customer;

public interface CustomerRespository extends JpaRepository<Customer,Long>{
    public List<Customer> findAll();
    public Customer findByPhoneNum(String phoneNum);
}
