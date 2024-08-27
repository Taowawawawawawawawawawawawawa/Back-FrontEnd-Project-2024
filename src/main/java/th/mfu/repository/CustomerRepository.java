package th.mfu.repository;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.CustomerTemp;

public interface CustomerRepository extends CrudRepository<CustomerTemp,Long> {

}
