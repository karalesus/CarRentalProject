package ru.rutmiit.controller;

import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.rutmiit.dto.CarDTO;
import ru.rutmiit.service.implementations.CarServiceImpl;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    CarServiceImpl carServiceImpl;

    @GetMapping("/carsSortByAttribute")
    public List<CarDTO> carsSortByAttribute(
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "price",required = false) BigDecimal price) {

        CarDTO carDTO = new CarDTO(brand, type, color, price);
        return carServiceImpl.getCarsByAttributes(carDTO);
    }

}
