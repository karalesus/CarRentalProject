package ru.rutmiit.domain;

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
    private List<Rental> rentals;

    public Payment(BigDecimal price, OffsetDateTime dateTime, PaymentStatus paymentStatus, Client client, List<Rental> rentals) {
        this.price = price;
        this.dateTime = OffsetDateTime.now();
        this.paymentStatus = PaymentStatus.PENDING;
        this.client = client;
        this.rentals = rentals;
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

    @Column(name = "payment_date")
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

    @OneToMany(mappedBy = "payment", targetEntity = Rental.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
