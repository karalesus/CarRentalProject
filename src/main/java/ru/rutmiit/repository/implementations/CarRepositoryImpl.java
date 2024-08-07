package ru.rutmiit.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.rutmiit.domain.Car;
import ru.rutmiit.repository.BaseRepository;
import ru.rutmiit.repository.CarRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class CarRepositoryImpl extends BaseRepository<Car, Integer> implements CarRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CarRepositoryImpl(Class<Car> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Car> getAvailableCarsByStatus(boolean isAvailable) {
        return entityManager.createQuery("SELECT c FROM Car c WHERE c.available = :isAvailable", Car.class)
                .setParameter("isAvailable", isAvailable)
                .getResultList();
    }

    public List<Car> getAvailableCarsByDate(LocalDate startDate, LocalDate finishDate) {
        return entityManager.createQuery("SELECT c FROM Car c WHERE c.id NOT IN (" +
                        "SELECT r.car.id FROM Rental r WHERE " +
                        "r.car.available = false OR " +
                        "(r.startDate <= :finishDate AND r.finishDate >= :startDate))", Car.class)
                .setParameter("startDate", startDate)
                .setParameter("finishDate", finishDate)
                .getResultList();
    }


    @Override
    public List<Car> getCarsByAttributes(String brand, String type, String color, BigDecimal price) {
        return entityManager.createQuery("SELECT c FROM Car c WHERE (:brand is null OR c.brand=:brand) AND (:type is null OR c.type=:type) AND (:color is null OR c.color=:color) AND (:price is null OR c.price=:price) order by c.id asc", Car.class)
                .setParameter("brand", brand)
                .setParameter("type", type)
                .setParameter("color", color)
                .setParameter("price", price)
                .getResultList();
    }
}
