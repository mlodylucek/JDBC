package pl.edu.wszib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wszib.model.Hardware;

public interface HardwareRepository extends JpaRepository<Hardware,Long> {
}
