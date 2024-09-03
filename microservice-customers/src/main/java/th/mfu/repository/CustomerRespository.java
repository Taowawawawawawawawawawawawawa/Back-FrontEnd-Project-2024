package th.mfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import th.mfu.domain.Customer;

public interface CustomerRespository extends JpaRepository<Customer,Long>{

}
