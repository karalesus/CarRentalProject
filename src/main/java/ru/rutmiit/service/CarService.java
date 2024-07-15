package ru.rutmiit.service;

import ru.rutmiit.domain.Car;
import ru.rutmiit.dto.CarDTO;

import java.time.LocalDate;
import java.util.List;

public interface CarService {

    List<Car> getAvailableCarsByStatus(boolean availability);

    List<Car> getAvailableCarsByDate(LocalDate startDate, LocalDate finishDate);

//    List<Car> getRentalCarsByDate(LocalDate startDate, LocalDate finishDate);

    void setCarStatusUnavailableOrAvailable(int id, boolean isAvailable);

    List<Car> getCarsByAttributes(Car car);
}
