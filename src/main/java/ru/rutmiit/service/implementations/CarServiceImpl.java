package ru.rutmiit.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rutmiit.domain.Car;
import ru.rutmiit.dto.CarDTO;
import ru.rutmiit.repository.implementations.CarRepositoryImpl;
import ru.rutmiit.service.CarService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepositoryImpl carRepositoryImpl;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CarDTO> getAvailableCarsByStatus(boolean isAvailable) {
        return carRepositoryImpl.getAvailableCarsByStatus(isAvailable).stream().map((s) -> modelMapper.map(s, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAvailableCarsByDate(LocalDate startDate, LocalDate finishDate) {
        return carRepositoryImpl.getAvailableCarsByDate(startDate, finishDate).stream().map((s) -> modelMapper.map(s, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getCarsByAttributes(CarDTO carDTO) {
        List<Car> cars = carRepositoryImpl.getCarsByAttributes(
                carDTO.getBrand(),
                carDTO.getType(),
                carDTO.getColor(),
                carDTO.getPrice()
        );
        return cars.stream()
                .map(c -> modelMapper.map(c, CarDTO.class))
                .collect(Collectors.toList());
    }

}
