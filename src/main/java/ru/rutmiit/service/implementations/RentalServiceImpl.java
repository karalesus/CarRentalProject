package ru.rutmiit.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rutmiit.domain.*;
import ru.rutmiit.domain.compositeKeys.RentalAssistKeys;
import ru.rutmiit.domain.enums.EventType;
import ru.rutmiit.domain.enums.PaymentStatus;
import ru.rutmiit.dto.PaymentDTO;
import ru.rutmiit.dto.RentalDTO;
import ru.rutmiit.exception.NoAvailableCarsException;
import ru.rutmiit.repository.*;
import ru.rutmiit.repository.implementations.*;
import ru.rutmiit.service.RentalService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RentalServiceImpl implements RentalService {


    @Autowired
    private RentalRepositoryImpl rentalRepositoryImpl;
    @Autowired
    private CarRepositoryImpl carRepositoryImpl;
    @Autowired
    private ClientRepositoryImpl clientRepositoryImpl;
    @Autowired
    private AssistRepository assistRepository;
    @Autowired
    private PaymentRepositoryImpl paymentRepositoryImpl;
    @Autowired
    private RentalAssistRepositoryImpl rentalAssistRepositoryImpl;
    @Autowired
    private PaymentServiceImpl paymentServiceImpl;
    @Autowired
    private ModelMapper modelMapper;

    private final Random random = new Random();

    @Override
    public BigDecimal calculateTotalCost(Car car, LocalDate startDate, LocalDate finishDate, List<Assist> assists) {
        long days = ChronoUnit.DAYS.between(startDate, finishDate);
        BigDecimal baseCost = car.getPrice().multiply(BigDecimal.valueOf(days));
        BigDecimal additionalCost = calculateAssistCost(assists);
        return baseCost.add(additionalCost);
    }

    @Override
    public BigDecimal calculateAssistCost(List<Assist> assists) {
        BigDecimal totalAdditionalCost = BigDecimal.ZERO;
        for (Assist assist : assists) {
            totalAdditionalCost = totalAdditionalCost.add(assist.getPrice());
        }
        return totalAdditionalCost;
    }

    public RentalDTO rentCar(RentalDTO rentalDTO) {
        Integer clientId = rentalDTO.getClientId();
        Integer carId = rentalDTO.getCarId();
        LocalDate startDate = rentalDTO.getStartDate();
        LocalDate finishDate = rentalDTO.getFinishDate();
        String deliveryPlace = rentalDTO.getDeliveryPlace();
        System.out.println(deliveryPlace);
        LocalTime deliveryTime = rentalDTO.getDeliveryTime();
        EventType eventType = rentalDTO.getEventType();

        Client client = clientRepositoryImpl.findById(Client.class, clientId);
        Car car = carRepositoryImpl.findById(Car.class, carId);


        if (!car.isAvailable()) {
            throw new NoAvailableCarsException("Нет доступных машин для аренды");
        }
        if (carRepositoryImpl.getAvailableCarsByDate(startDate, finishDate).isEmpty()) {
            throw new NoAvailableCarsException("Нет доступных машин на эти даты");
        }
        List<Assist> chosenAssists = choiceAssists(assistRepository.findAllAssists());

        BigDecimal totalPrice = calculateTotalCost(car, startDate, finishDate, chosenAssists);

        paymentServiceImpl.createPayment(totalPrice, client.getId());

        Payment lastPendingPayment = paymentRepositoryImpl.getLatestPendingPayment();
        PaymentDTO paymentDTO = modelMapper.map(lastPendingPayment, PaymentDTO.class);

        boolean isPaid = paymentServiceImpl.decidePay();
        PaymentStatus updatedPaymentStatus;

        if (isPaid) { updatedPaymentStatus = PaymentStatus.COMPLETED; }
        else { updatedPaymentStatus = PaymentStatus.CANCELLED; }

        paymentServiceImpl.updatePaymentStatus(paymentDTO, updatedPaymentStatus);
        lastPendingPayment.setPaymentStatus(updatedPaymentStatus);
        paymentDTO.setPaymentStatus(updatedPaymentStatus);

        Rental rental = new Rental(startDate, finishDate, deliveryPlace, deliveryTime, eventType, client, car, lastPendingPayment);
        rentalRepositoryImpl.save(rental);

        List<RentalAssist> rentalAssists = new ArrayList<>();
        for (Assist assist : chosenAssists) {
            RentalAssistKeys rentalAssistKeys = new RentalAssistKeys();
            rentalAssistKeys.setRental(rental);
            rentalAssistKeys.setAssist(assist);
            RentalAssist rentalAssist = new RentalAssist(rentalAssistKeys);
            rentalAssistRepositoryImpl.save(rentalAssist);
            rentalAssists.add(rentalAssist);
        }
        rental.setRentalAssist(rentalAssists);

        rentalDTO = modelMapper.map(rental, RentalDTO.class);

        paymentServiceImpl.updateRentals(paymentDTO, rentalDTO);
        rentalDTO.setPayment(paymentDTO);

        return rentalDTO;
    }

    public List<Assist> choiceAssists(List<Assist> availableAssists) {
        int count = random.nextInt(4);
        List<Assist> chosenAssists = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Assist assist = availableAssists.get(random.nextInt(availableAssists.size()));
            chosenAssists.add(assist);
        }

        return chosenAssists;
    }
}
