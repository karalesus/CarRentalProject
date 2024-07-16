package ru.rutmiit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rutmiit.domain.Car;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface CarRepository {

    List<Car> getAvailableCarsByStatus(boolean isAvailable);

    List<Car> getAvailableCarsByDate(LocalDate startDate, LocalDate finishDate);

    List<Car> getCarsByAttributes(String brand, String type, String color, BigDecimal price);
}
