package ru.rutmiit.dto;

import ru.rutmiit.domain.Car;
import ru.rutmiit.domain.Client;
import ru.rutmiit.domain.Payment;
import ru.rutmiit.domain.RentalAssist;
import ru.rutmiit.domain.enums.EventType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class RentalDTO extends IdDTO {

    private LocalDate startDate;
    private LocalDate finishDate;
    private String deliveryPlace;
    private LocalTime deliveryTime;
    private EventType eventType;
    private ClientDTO client;
    private CarDTO car;
    private PaymentDTO payment;
    private List<RentalAssist> rentalAssist;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public LocalTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public List<RentalAssist> getRentalAssist() {
        return rentalAssist;
    }

    public void setRentalAssist(List<RentalAssist> rentalAssist) {
        this.rentalAssist = rentalAssist;
    }
}
