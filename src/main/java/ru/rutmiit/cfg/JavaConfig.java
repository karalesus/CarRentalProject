package ru.rutmiit.cfg;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rutmiit.domain.*;
import ru.rutmiit.dto.PaymentDTO;
import ru.rutmiit.dto.RentalDTO;

import java.util.stream.Collectors;

@Configuration
public class JavaConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Payment.class, PaymentDTO.class)
                .addMapping(src -> src.getClient().getId(), PaymentDTO::setClientId);
        modelMapper.createTypeMap(Rental.class, RentalDTO.class)
                .addMapping(src -> src.getRentalAssist().stream().map(RentalAssist::getId).collect(Collectors.toList()), RentalDTO::setRentalAssist);

        return modelMapper;
    }

    @Bean
    public Class<Car> carClass() {
        return Car.class;
    }

    @Bean
    public Class<Rental> rentalClass() {
        return Rental.class;
    }

    @Bean
    public Class<Assist> assistClass() {
        return Assist.class;
    }

    @Bean
    public Class<RentalAssist> rentalAssistClass() {
        return RentalAssist.class;
    }

    @Bean
    public Class<Client> clientClass() {
        return Client.class;
    }
}
