package ru.rutmiit.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.rutmiit.domain.Rental;
import ru.rutmiit.repository.BaseRepository;
import ru.rutmiit.repository.RentalRepository;

import java.util.List;

@Repository
public class RentalRepositoryImpl extends BaseRepository<Rental, Integer> implements RentalRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public RentalRepositoryImpl(Class<Rental> rentalClass) {
        super(rentalClass);
    }

}

