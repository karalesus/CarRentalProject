package ru.rutmiit.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rutmiit.domain.*;
import ru.rutmiit.domain.compositeKeys.RentalAssistKeys;
import ru.rutmiit.domain.enums.EventType;
import ru.rutmiit.domain.enums.PaymentStatus;
import ru.rutmiit.repository.*;
import ru.rutmiit.repository.implementations.PaymentRepositoryImpl;
import ru.rutmiit.repository.implementations.RentalAssistRepositoryImpl;
import ru.rutmiit.repository.implementations.RentalRepositoryImpl;
import ru.rutmiit.service.RentalService;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepositoryImpl rentalRepositoryImpl;
    private final CarRepository carRepository;
    private final AssistRepository assistRepository;
    private final ClientRepository clientRepository;
    private final PaymentRepositoryImpl paymentRepositoryImpl;
    private final RentalAssistRepositoryImpl rentalAssistRepositoryImpl;
    private final ModelMapper modelMapper;

    @Autowired
    public RentalServiceImpl(RentalRepositoryImpl rentalRepositoryImpl, CarRepository carRepository, AssistRepository assistRepository, ClientRepository clientRepository, PaymentRepositoryImpl paymentRepositoryImpl, RentalAssistRepositoryImpl rentalAssistRepositoryImpl, ModelMapper modelMapper) {
        this.rentalRepositoryImpl = rentalRepositoryImpl;
        this.carRepository = carRepository;
        this.assistRepository = assistRepository;
        this.clientRepository = clientRepository;
        this.paymentRepositoryImpl = paymentRepositoryImpl;
        this.rentalAssistRepositoryImpl = rentalAssistRepositoryImpl;
        this.modelMapper = modelMapper;
    }

    @Override
    public BigDecimal calculateTotalCost(Car car, LocalDate startDate, LocalDate finishDate, List<String> assists) {
        long days = Duration.between(startDate, finishDate).toDays();
        BigDecimal baseCost = car.getPrice().multiply(BigDecimal.valueOf(days));
        BigDecimal additionalCost = calculateAssistCost(assists);
        return baseCost.add(additionalCost);
    }

    @Override
    public BigDecimal calculateAssistCost(List<String> assists) {
        BigDecimal totalAdditionalCost = BigDecimal.ZERO;
        for (String assistName : assists) {
            Assist assist = assistRepository.findByName(assistName)
                    .orElseThrow(() -> new IllegalArgumentException("Сервис не найден: " + assistName));
            totalAdditionalCost = totalAdditionalCost.add(assist.getPrice());
        }
        return totalAdditionalCost;
    }

    public Rental rentCar(Client client, Car car, List<Assist> assists, LocalDate startDate, LocalDate finishDate, String deliveryPlace, LocalTime deliveryTime, EventType eventType) {
        if (!car.isAvailable() || !carRepository.getAvailableCarsByDate(startDate, finishDate).contains(car)) {
            throw new IllegalStateException("Нет доступных машин");
        }

        // Рассчитываем итоговую стоимость аренды с услугами
        BigDecimal totalPrice = car.getPrice();
        for (Assist assist : assists) {
            totalPrice = totalPrice.add(assist.getPrice());
        }

        // Создаем запись о платеже
        Payment payment = new Payment(totalPrice, OffsetDateTime.now(), PaymentStatus.PENDING, client, null);
        paymentRepositoryImpl.save(payment);

        // Создаем запись об аренде
        Rental rental = new Rental(startDate, finishDate, deliveryPlace, deliveryTime, eventType, client, car, payment);
        rentalRepositoryImpl.save(rental);

        // Соединяем услуги и аренду и создаем запись услуги-аренды
        for (Assist assist : assists) {
            RentalAssistKeys rentalAssistKeys = new RentalAssistKeys();
            rentalAssistKeys.setRental(rental);
            rentalAssistKeys.setAssist(assist);
            RentalAssist rentalAssist = new RentalAssist(rentalAssistKeys);
            rentalAssistRepositoryImpl.save(rentalAssist);
        }

        // Устанавливаем в аренду платеж
        payment.setRentals(List.of(rental));
        paymentRepositoryImpl.save(payment);

        return rental;
    }

    public void completePayment(Payment payment) {
        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        paymentRepositoryImpl.save(payment);
    }

    public void cancelPayment(Payment payment) {
        payment.setPaymentStatus(PaymentStatus.CANCELLED);
        paymentRepositoryImpl.save(payment);
    }
}
