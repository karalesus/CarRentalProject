package ru.rutmiit.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "assist")
public class Assist extends IdEntity {

    private String name;
    private String description;
    private BigDecimal price;
    private List<RentalAssist> rentalAssist;

    public Assist(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    protected Assist() {}

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @OneToMany(mappedBy = "id.assist",
            targetEntity = RentalAssist.class)
    public List<RentalAssist> getRentalAssist() {
        return rentalAssist;
    }

    public void setRentalAssist(List<RentalAssist> rentalAssist) {
        this.rentalAssist = rentalAssist;
    }
}
