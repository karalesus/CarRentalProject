package ru.rutmiit.domain.compositeKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import ru.rutmiit.domain.Assist;
import ru.rutmiit.domain.Rental;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RentalAssistKeys implements Serializable {
    private Rental rental;
    private Assist assist;

    @ManyToOne
    @JoinColumn(name = "rental_id", nullable = false)
    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
    @ManyToOne
    @JoinColumn(name = "assist_id", nullable = false)
    public Assist getAssist() {
        return assist;
    }

    public void setAssist(Assist assist) {
        this.assist = assist;
    }

    @Override
    public String toString() {
        return "RentalAssistKeys{" +
                "rental=" + rental +
                ", assist=" + assist +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalAssistKeys that = (RentalAssistKeys) o;
        return Objects.equals(rental, that.rental) && Objects.equals(assist, that.assist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rental, assist);
    }
}
