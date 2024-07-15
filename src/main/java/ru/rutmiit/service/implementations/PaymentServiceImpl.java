package ru.rutmiit.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rutmiit.domain.Payment;
import ru.rutmiit.domain.enums.PaymentStatus;
import ru.rutmiit.repository.implementations.PaymentRepositoryImpl;
import ru.rutmiit.repository.implementations.RentalRepositoryImpl;
import ru.rutmiit.service.PaymentService;

import java.time.OffsetDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepositoryImpl paymentRepositoryImpl;

    @Override
    public void completePayment(Payment payment) {
        if (checkPayment(payment)) {
            paymentRepositoryImpl.updateStatus(payment.getId(), PaymentStatus.COMPLETED);
        }
    }

    @Override
    public void cancelPayment(Payment payment) {
        if (!checkPayment(payment)) {
            paymentRepositoryImpl.updateStatus(payment.getId(), PaymentStatus.CANCELLED);
        }
    }

    @Override
    public boolean checkPayment(Payment payment) {

        OffsetDateTime currentTime = OffsetDateTime.now();

        //Оплата в течение часа после создания платежа
        OffsetDateTime paymentDueTime = payment.getDateTime().plusHours(1);

        if (currentTime.isAfter(paymentDueTime)) {
            return false;
        } else {
            return true;
        }
}

}
