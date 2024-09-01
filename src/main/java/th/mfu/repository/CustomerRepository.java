package th.mfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
