package ru.rutmiit.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.rutmiit.domain.Payment;
import ru.rutmiit.repository.BaseRepository;
import ru.rutmiit.repository.PaymentRepository;

import java.util.List;

@Repository
public class PaymentRepositoryImpl extends BaseRepository<Payment, Integer> implements PaymentRepository {

    @PersistenceContext
    EntityManager entityManager;

    public PaymentRepositoryImpl() {
        super(Payment.class);
    }

    @Override
    public List<Payment> findByClientId(int clientId) {
        return entityManager.createQuery("SELECT p from Payment p JOIN p.client c where c.id = :clientId", Payment.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }
}
