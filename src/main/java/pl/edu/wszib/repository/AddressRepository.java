package pl.edu.wszib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wszib.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {

}
