package ru.rutmiit.repository.implementations;

import ru.rutmiit.domain.RentalAssist;
import ru.rutmiit.repository.BaseRepository;

public class RentalAssistRepositoryImpl extends BaseRepository<RentalAssist, Integer> {
    public RentalAssistRepositoryImpl(Class<RentalAssist> rentalAssistClass) {
        super(rentalAssistClass);
    }
}
