package pl.edu.wszib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wszib.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
