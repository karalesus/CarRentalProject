package ru.rutmiit.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.domain.Client;
import ru.rutmiit.domain.Payment;
import ru.rutmiit.domain.Rental;
import ru.rutmiit.domain.enums.PaymentStatus;
import ru.rutmiit.dto.PaymentDTO;
import ru.rutmiit.dto.RentalDTO;
import ru.rutmiit.repository.implementations.ClientRepositoryImpl;
import ru.rutmiit.repository.implementations.PaymentRepositoryImpl;
import ru.rutmiit.service.PaymentService;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Random;

@Service
public class DomainPaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepositoryImpl paymentRepositoryImpl;
    @Autowired
    private ClientRepositoryImpl clientRepositoryImpl;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void completePayment(Payment payment) {
        if (checkPayment(payment)) {
            paymentRepositoryImpl.updateStatus(payment.getId(), PaymentStatus.COMPLETED);
        }
    }

    @Override
    public void cancelPayment(Payment payment) {
        if (!checkPayment(payment)) {
            paymentRepositoryImpl.updateStatus(payment.getId(), PaymentStatus.CANCELLED);
        }
    }

    @Override
    public void createPayment(BigDecimal totalCost, int clientDTOId) {
        Payment payment = new Payment(totalCost, OffsetDateTime.now(),
                PaymentStatus.PENDING, clientRepositoryImpl.findById(Client.class, clientDTOId));
        paymentRepositoryImpl.save(payment);
    }

    @Override
    public void updatePaymentStatus(PaymentDTO paymentDTO, PaymentStatus newStatus) {
        Payment payment = mapPaymentDTOToEntity(paymentDTO);
        payment.setPaymentStatus(newStatus);
    }

    @Override
    @Transactional
    public void updateRentals(PaymentDTO paymentDTO, RentalDTO rentalDTO) {
        Payment payment = mapPaymentDTOToEntity(paymentDTO);
        Rental rental = payment.getRentals();
        payment.setRentals(rental);
        paymentRepositoryImpl.update(payment);
    }


    @Override
    public boolean decidePay() {
        Random random = new Random();
        return random.nextDouble() <= 0.8;
    }


    @Override
    public boolean checkPayment(Payment payment) {

        OffsetDateTime currentTime = OffsetDateTime.now();

        //Оплата в течение часа после создания платежа
        OffsetDateTime paymentDueTime = payment.getDateTime().plusHours(1);

        if (currentTime.isAfter(paymentDueTime)) {
            return false;
        } else {
            return true;
        }
    }

    private Payment mapPaymentDTOToEntity(PaymentDTO paymentDTO) {
        Payment mappedPayment = modelMapper.map(paymentDTO, Payment.class);
        Client client = clientRepositoryImpl.findById(Client.class, paymentDTO.getClientId());
        mappedPayment.setClient(client);
        return mappedPayment;
    }
}
