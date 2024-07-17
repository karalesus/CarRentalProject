package ru.rutmiit.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import ru.rutmiit.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "payment")
public class Payment extends IdEntity {
    private BigDecimal price;
    private OffsetDateTime dateTime; // timestamp + timezone
    private PaymentStatus paymentStatus;
    private Client client;
    private Rental rental;

    public Payment(BigDecimal price, OffsetDateTime dateTime, PaymentStatus paymentStatus, Client client) {
        this.price = price;
        this.dateTime = OffsetDateTime.now();
        this.paymentStatus = PaymentStatus.PENDING;
        this.client = client;
    }

    protected Payment() {
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "payment_date", nullable = false)
    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToOne(mappedBy = "payment", targetEntity = Rental.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Rental getRentals() {
        return rental;
    }

    public void setRentals(Rental rentals) {
        this.rental = rental;
    }
}
