package ru.rutmiit.domain;

import jakarta.persistence.*;
import ru.rutmiit.domain.enums.CarStatus;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "car")
public class Car extends IdEntity {
    private String brand;
    private String model;
    private String type;
    private int releaseDate;
    private String color;
    private int mileage;
    private String licensePlate;
    private BigDecimal price;
    private CarStatus carStatus;
    private List<Rental> rentals;

    public Car(String brand, String model, String type, int releaseDate, String color, int mileage, String licensePlate, BigDecimal price, CarStatus carStatus) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.releaseDate = releaseDate;
        this.color = color;
        this.mileage = mileage;
        this.licensePlate = licensePlate;
        this.price = price;
        this.carStatus = carStatus;
    }

    public Car() {
    }

    @Column(nullable = false)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "release_date", nullable = false)
    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Column(nullable = false)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(nullable = false)
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Column(name = "license_plate", nullable = false)
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    @OneToMany(mappedBy = "car", targetEntity = Rental.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
