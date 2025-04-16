package com.example.practical.api.server;

import com.example.practical.entity.Car;
import com.example.practical.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car/v1")
public class CarApi {

    private final CarService carService;
    private static final Logger logger = LoggerFactory.getLogger(CarApi.class);

    @Autowired
    public CarApi(CarService carService) {
        this.carService = carService;
    }

    // When this API is called (in the /random path), its output is JSON. And in the HTTP response header, the Content-Type value will be application/json.
    @GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car getRandomCar() {
        return carService.generateCar();
    }

    @PostMapping(value = "/echo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String echo(@RequestBody Car car) {
        logger.info("Car is {}", car.toString());
        return car.toString();
    }

}
