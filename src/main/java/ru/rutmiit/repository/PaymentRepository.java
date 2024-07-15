package ru.rutmiit.repository;

import org.springframework.stereotype.Repository;
import ru.rutmiit.domain.Payment;

import java.util.List;

@Repository
public interface PaymentRepository {

    List<Payment> findByClientId(int clientId);
}
