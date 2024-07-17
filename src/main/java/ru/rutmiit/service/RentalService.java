package ru.rutmiit.service;

import ru.rutmiit.domain.*;
import ru.rutmiit.dto.RentalDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;



public interface RentalService {

    BigDecimal calculateTotalCost(Car car, LocalDate startDate, LocalDate finishDate, List<Assist> assist);

    BigDecimal calculateAssistCost(List<Assist> additionalServices);

    RentalDTO rentCar(RentalDTO rentalDTO);
}

