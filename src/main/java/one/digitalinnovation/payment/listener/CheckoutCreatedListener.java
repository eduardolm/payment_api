package one.digitalinnovation.payment.listener;

import one.digitalinnovation.ecommerce.checkout.event.CheckoutCreatedEvent;
import one.digitalinnovation.ecommerce.payment.event.PaymentCreatedEvent;
import one.digitalinnovation.payment.model.Payment;
import one.digitalinnovation.payment.service.PaymentService;
import one.digitalinnovation.payment.streaming.CheckoutProcessor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class CheckoutCreatedListener {

    private final CheckoutProcessor checkoutProcessor;

    private final PaymentService paymentService;

    public CheckoutCreatedListener(CheckoutProcessor checkoutProcessor, PaymentService paymentService) {
        this.checkoutProcessor = checkoutProcessor;
        this.paymentService = paymentService;
    }

    @StreamListener(CheckoutProcessor.INPUT)
    public void handle(CheckoutCreatedEvent event) {

        final Payment payment = paymentService.create(event).orElseThrow();
        final PaymentCreatedEvent paymentCreatedEvent = PaymentCreatedEvent.newBuilder()
                .setCheckoutCode(payment.getCheckoutCode())
                .setPaymentCode(payment.getCode())
                .build();

        checkoutProcessor.output().send(MessageBuilder.withPayload(paymentCreatedEvent).build());
    }
}
