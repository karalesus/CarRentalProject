package ru.rutmiit.dto;

import ru.rutmiit.domain.Payment;
import ru.rutmiit.domain.Rental;

import java.util.List;

public class ClientDTO extends IdDTO {

    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;
    private int driversLicense;
    private PaymentDTO payment;
    private RentalDTO rental;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(int driversLicense) {
        this.driversLicense = driversLicense;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public RentalDTO getRental() {
        return rental;
    }

    public void setRental(RentalDTO rental) {
        this.rental = rental;
    }
}
