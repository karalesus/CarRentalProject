package ru.rutmiit.service;

import org.springframework.stereotype.Service;
import ru.rutmiit.domain.Car;
import ru.rutmiit.dto.CarDTO;

import java.time.LocalDate;
import java.util.List;

@Service
public interface CarService {

    List<CarDTO> getAvailableCarsByStatus(boolean availability);

    List<CarDTO> getAvailableCarsByDate(LocalDate startDate, LocalDate finishDate);

    List<CarDTO> getCarsByAttributes(CarDTO carDTO);
}
