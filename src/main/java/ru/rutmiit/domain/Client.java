package ru.rutmiit.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "client")
public class Client extends IdEntity {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;
    private int driversLicense;
    private List<Payment> payments;
    private List<Rental> rentals;

    public Client(String firstName, String lastName, String patronymic, String phoneNumber, int driversLicense) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.driversLicense = driversLicense;
    }

    protected Client() {}

    @Column(name = "first_name", nullable = false)

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Column(name = "phone_number", nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "drivers_license")
    public int getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(int driversLicense) {
        this.driversLicense = driversLicense;
    }

    @OneToMany(mappedBy = "client", targetEntity = Payment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
    @OneToMany(mappedBy = "client", targetEntity = Rental.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
