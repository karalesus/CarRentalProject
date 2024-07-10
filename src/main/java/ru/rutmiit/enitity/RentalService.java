package ru.rutmiit.enitity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.rutmiit.enitity.compositeKeys.RentalServiceKeys;

@Entity
@Table(name = "rental_service")
public class RentalService {

    RentalServiceKeys id;

    @EmbeddedId
    public RentalServiceKeys getId() {
        return id;
    }

    public void setId(RentalServiceKeys id) {
        this.id = id;
    }

    public RentalService(RentalServiceKeys id) {
        this.id = id;
    }

    public RentalService() {
    }
}
