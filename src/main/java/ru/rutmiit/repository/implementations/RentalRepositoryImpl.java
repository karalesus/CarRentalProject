package ru.rutmiit.repository.implementations;

import org.springframework.stereotype.Repository;
import ru.rutmiit.domain.Rental;
import ru.rutmiit.repository.BaseRepository;

@Repository
public class RentalRepositoryImpl extends BaseRepository<Rental, Integer> {
    public RentalRepositoryImpl(Class<Rental> rentalClass) {
        super(rentalClass);
    }
}
