package ru.rutmiit.service;

import ru.rutmiit.domain.*;
import ru.rutmiit.domain.enums.EventType;
import ru.rutmiit.dto.CarDTO;
import ru.rutmiit.dto.RentalDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface RentalService {

    BigDecimal calculateTotalCost(Car car, LocalDate startDate, LocalDate finishDate, List<String> assist);

    BigDecimal calculateAssistCost(List<String> additionalServices);

    Rental rentCar(Client client, Car car, List<Assist> assists, LocalDate startDate, LocalDate finishDate, String deliveryPlace, LocalTime deliveryTime, EventType eventType);
}

