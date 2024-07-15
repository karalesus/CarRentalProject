package ru.rutmiit.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rutmiit.domain.Payment;
import ru.rutmiit.domain.enums.PaymentStatus;
import ru.rutmiit.repository.implementations.PaymentRepositoryImpl;
import ru.rutmiit.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepositoryImpl paymentRepositoryImpl;

    @Autowired
    public PaymentServiceImpl(PaymentRepositoryImpl paymentRepositoryImpl) {
        this.paymentRepositoryImpl = paymentRepositoryImpl;
    }

    public void completePayment(Payment payment) {
        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        paymentRepositoryImpl.save(payment);
    }

    public void cancelPayment(Payment payment) {
        payment.setPaymentStatus(PaymentStatus.CANCELLED);
        paymentRepositoryImpl.save(payment);
    }
}
