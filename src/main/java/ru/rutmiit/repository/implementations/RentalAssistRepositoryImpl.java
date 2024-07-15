package ru.rutmiit.repository.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.rutmiit.domain.RentalAssist;
import ru.rutmiit.repository.BaseRepository;

@Repository
public class RentalAssistRepositoryImpl extends BaseRepository<RentalAssist, Integer> {

    public RentalAssistRepositoryImpl(Class<RentalAssist> rentalAssistClass) {
        super(rentalAssistClass);
    }
}
