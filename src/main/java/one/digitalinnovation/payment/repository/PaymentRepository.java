package one.digitalinnovation.payment.repository;

import one.digitalinnovation.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
