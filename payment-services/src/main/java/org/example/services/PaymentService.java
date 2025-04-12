package org.example.services;

import org.example.model.Payment;
import org.example.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(Long ticketId, Double amount) {
        Payment payment = new Payment();
        payment.setTicketId(ticketId);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setStatus("Completed");
        payment.setAmountPaid(amount);

        return paymentRepository.save(payment);
    }
}
