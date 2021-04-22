package one.digitalinnovation.payment.service;

import one.digitalinnovation.ecommerce.checkout.event.CheckoutCreatedEvent;
import one.digitalinnovation.payment.model.Payment;

import java.util.Optional;

public interface PaymentService {

    Optional<Payment> create(CheckoutCreatedEvent checkoutCreatedEvent);
}
