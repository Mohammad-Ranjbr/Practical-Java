package com.example.practical.api.server;

import com.example.practical.entity.Car;
import com.example.practical.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
        return carService.generateCar("");
    }

    // This API only accepts requests with a Content-Type of application/json.
    @PostMapping(value = "/echo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String echo(@RequestBody Car car) {
        logger.info("Car is {}", car.toString());
        return car.toString();
    }

    @GetMapping(value = "/random-cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> randomCars(){
        List<Car> cars = new ArrayList<>();
        for (int i=0; i < ThreadLocalRandom.current().nextInt(1, 10); i++) {
            cars.add(carService.generateCar(""));
        }
        return cars;
    }

}
