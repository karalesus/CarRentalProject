package ru.rutmiit.repository.implementations;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.domain.Payment;
import ru.rutmiit.domain.enums.PaymentStatus;
import ru.rutmiit.repository.BaseRepository;
import ru.rutmiit.repository.PaymentRepository;

@Repository
public class PaymentRepositoryImpl extends BaseRepository<Payment, Integer> implements PaymentRepository {
    @PersistenceContext
    EntityManager entityManager;

    public PaymentRepositoryImpl() {
        super(Payment.class);
    }


    @Override
    public Payment getLatestPendingPayment() {
        String jpql = "SELECT p FROM Payment p WHERE p.paymentStatus = :status ORDER BY p.dateTime DESC";
        TypedQuery<Payment> query = entityManager.createQuery(jpql, Payment.class);
        query.setParameter("status", PaymentStatus.PENDING);
        query.getSingleResult();
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Платежа с таким статусом нет");
        }
    }
    @Override
    @Transactional
    public void updateStatus(int id, PaymentStatus paymentStatus) {
        entityManager.createQuery(
                        "UPDATE Payment p " +
                                " SET p.paymentStatus = :paymentStatus " +
                                " WHERE p.id = :id ", Payment.class)
                .setParameter("paymentStatus", paymentStatus)
                .setParameter("id", id)
                .executeUpdate();
    }
}
