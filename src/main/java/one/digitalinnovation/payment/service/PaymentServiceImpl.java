package one.digitalinnovation.payment.service;

import one.digitalinnovation.ecommerce.checkout.event.CheckoutCreatedEvent;
import one.digitalinnovation.payment.model.Payment;
import one.digitalinnovation.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> create(CheckoutCreatedEvent checkoutCreatedEvent) {

        final Payment payment = new Payment(UUID.randomUUID().toString(),
                checkoutCreatedEvent.getCheckoutCode().toString(),
                LocalDateTime.now(),
                LocalDateTime.now());

        paymentRepository.save(payment);
        return Optional.of(payment);
    }
}
