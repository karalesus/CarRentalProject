package ru.rutmiit.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.rutmiit.domain.compositeKeys.RentalServiceKeys;

@Entity
@Table(name = "rental_service")
public class RentalAssist {

    RentalServiceKeys id;

    @EmbeddedId
    public RentalServiceKeys getId() {
        return id;
    }

    public void setId(RentalServiceKeys id) {
        this.id = id;
    }

    public RentalAssist(RentalServiceKeys id) {
        this.id = id;
    }

    protected RentalAssist() {
    }
}
