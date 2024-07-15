package ru.rutmiit.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rutmiit.domain.Car;
import ru.rutmiit.dto.CarDTO;
import ru.rutmiit.repository.implementations.CarRepositoryImpl;
import ru.rutmiit.service.CarService;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepositoryImpl carRepositoryImpl;

    @Autowired
    public CarServiceImpl(CarRepositoryImpl carRepositoryImpl) {
        this.carRepositoryImpl = carRepositoryImpl;
    }

    @Override
    public List<Car> getAvailableCarsByStatus(boolean isAvailable) {
        return carRepositoryImpl.getAvailableCarsByStatus(isAvailable);
    }

    @Override
    public List<Car> getAvailableCarsByDate(LocalDate startDate, LocalDate finishDate) {
        return carRepositoryImpl.getAvailableCarsByDate(startDate, finishDate);
    }

    @Override
    public void setCarStatusUnavailableOrAvailable(int id, boolean isAvailable) {
            Car car = carRepositoryImpl.findById(Car.class, id);
            car.setAvailable(isAvailable);
            carRepositoryImpl.save(car);
    }

    @Override
    public List<Car> getCarsByAttributes(Car car) {
        return carRepositoryImpl.getCarsByAttributes(car.getBrand(), car.getType(), car.getReleaseDate(), car.getColor(), car.getPrice());
    }
}
