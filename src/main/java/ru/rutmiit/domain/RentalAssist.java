package ru.rutmiit.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.rutmiit.domain.compositeKeys.RentalAssistKeys;

@Entity
@Table(name = "rental_assist")
public class RentalAssist {

    private RentalAssistKeys id;

    @EmbeddedId
    public RentalAssistKeys getId() {
        return id;
    }

    public void setId(RentalAssistKeys id) {
        this.id = id;
    }

    public RentalAssist(RentalAssistKeys id) {
        this.id = id;
    }

    protected RentalAssist() {
    }
}
