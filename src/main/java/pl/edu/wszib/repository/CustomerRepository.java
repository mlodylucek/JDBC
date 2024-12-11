package pl.edu.wszib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wszib.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
