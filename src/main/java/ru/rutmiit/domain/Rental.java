package ru.rutmiit.domain;


import jakarta.persistence.*;
import ru.rutmiit.domain.enums.EventType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "rental")
public class Rental extends IdEntity {

    private LocalDate startDate;
    private LocalDate finishDate;
    private String deliveryPlace;
    private LocalTime deliveryTime;
    private EventType eventType;
    private Client client;
    private Car car;
    private Payment payment;
    private List<RentalAssist> rentalAssist;

    public Rental(LocalDate startDate, LocalDate finishDate, String deliveryPlace, LocalTime deliveryTime, EventType eventType, Client client, Car car, Payment payment) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.deliveryPlace = deliveryPlace;
        this.deliveryTime = deliveryTime;
        this.eventType = eventType;
        this.client = client;
        this.car = car;
        this.payment = payment;
    }

    protected Rental() {
    }

    @Column(name = "start_date", nullable = false)
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column(name = "finish_date", nullable = false)
    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    @Column(name = "delivery_place", nullable = false)
    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    @Column(name = "delivery_time", nullable = false)
    public LocalTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Column(name = "event_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @OneToMany(mappedBy = "id.rental",
            targetEntity = RentalAssist.class)
    public List<RentalAssist> getRentalAssist() {
        return rentalAssist;
    }

    public void setRentalAssist(List<RentalAssist> rentalAssist) {
        this.rentalAssist = rentalAssist;
    }
}
