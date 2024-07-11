package ru.rutmiit.domain;


import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "rental")
public class Rental extends IdEntity {

    private Date startDate;
    private Date finishDate;
    private String deliveryPlace;
    private LocalTime deliveryTime;
    private Client client;
    private Car car;
    private Payment payment;

    public Rental(Date startDate, Date finishDate, String deliveryPlace, LocalTime deliveryTime, Client client, Car car, Payment payment) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.deliveryPlace = deliveryPlace;
        this.deliveryTime = deliveryTime;
        this.client = client;
        this.car = car;
        this.payment = payment;
    }

    public Rental() {
    }

    @Column(name = "start_date", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "finish_date", nullable = false)
    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
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
}
