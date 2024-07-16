package ru.rutmiit.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.domain.Payment;
import ru.rutmiit.domain.enums.PaymentStatus;

import java.util.List;

@Repository
public interface PaymentRepository {

    Payment getLatestPendingPayment();

    @Transactional
    void updateStatus(int id, PaymentStatus paymentStatus);
}
