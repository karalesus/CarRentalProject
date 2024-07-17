package ru.rutmiit.service;

import ru.rutmiit.domain.Payment;
import ru.rutmiit.domain.enums.PaymentStatus;
import ru.rutmiit.dto.PaymentDTO;
import ru.rutmiit.dto.RentalDTO;

import java.math.BigDecimal;

public interface PaymentService {
    void completePayment(Payment payment);
    void cancelPayment(Payment payment);
    void createPayment(BigDecimal totalCost, int clientId);
    void updatePaymentStatus(PaymentDTO paymentDTO, PaymentStatus paymentStatus);
    void updateRentals(PaymentDTO paymentDTO, RentalDTO rentalDTO);
    boolean decidePay();
    boolean checkPayment(Payment payment);
}
