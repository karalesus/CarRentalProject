package ru.rutmiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rutmiit.dto.CarDTO;
import ru.rutmiit.dto.RentalDTO;
import ru.rutmiit.service.implementations.CarServiceImpl;
import ru.rutmiit.service.implementations.DomainRentalServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private DomainRentalServiceImpl domainRentalServiceImpl;

    @Autowired
    private CarServiceImpl carServiceImpl;

    @GetMapping("/available-cars")
    public List<CarDTO> getAvailableCars(@RequestParam("startDate") LocalDate startDate, @RequestParam("finishDate") LocalDate finishDate) {
        return carServiceImpl.getAvailableCarsByDate(startDate, finishDate);
    }

    @PostMapping("/rent")
    public RentalDTO rentCar(@RequestBody RentalDTO rentalDTO) {
        return domainRentalServiceImpl.rentCar(rentalDTO);
    }
}

