package ru.rutmiit.service;

import org.springframework.stereotype.Service;
import ru.rutmiit.domain.*;
import ru.rutmiit.dto.RentalDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service

public interface RentalService {

    BigDecimal calculateTotalCost(Car car, LocalDate startDate, LocalDate finishDate, List<Assist> assist);

    BigDecimal calculateAssistCost(List<Assist> additionalServices);

    RentalDTO rentCar(RentalDTO rentalDTO);
}

