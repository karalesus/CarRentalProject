package ru.rutmiit.dto;

import ru.rutmiit.domain.Rental;

import java.math.BigDecimal;
import java.util.List;

public class CarDTO extends IdDTO {

    private String brand;
    private String type;
    private String color;
    private BigDecimal price;

    public CarDTO() {
    }

    public CarDTO(String brand, String type, String color, BigDecimal price) {
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
