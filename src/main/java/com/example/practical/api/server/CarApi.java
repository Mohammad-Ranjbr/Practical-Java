package com.example.practical.api.server;

import com.example.practical.api.response.ErrorResponse;
import com.example.practical.entity.Car;
import com.example.practical.repository.CarElasticRepository;
import com.example.practical.service.CarService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/car/v1")
public class CarApi {

    private final CarService carService;
    private final CarElasticRepository carElasticRepository;
    private static final Logger logger = LoggerFactory.getLogger(CarApi.class);

    @Autowired
    public CarApi(CarService carService, CarElasticRepository carElasticRepository) {
        this.carService = carService;
        this.carElasticRepository = carElasticRepository;
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

    @GetMapping("/count")
    public String count(){
        return String.format("There are %s cars", carElasticRepository.count());
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String add(@RequestBody Car car) {
        String id = carElasticRepository.save(car).getId();
        return String.format("Added car : %s", id);
    }

    @GetMapping("/{id}")
    public Car get(@PathVariable String id) {
        return carElasticRepository.findById(id).orElse(null);
    }

    @PutMapping(value = "/{id}")
    public String updateCar(@PathVariable String id, @RequestBody Car car) {
        System.out.println(id);
        car.setId(id);
        Car updatedCar = carElasticRepository.save(car);
        return String.format("Updated car : %s", updatedCar.getId());
    }

    @GetMapping(value = "/find-json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> findCarsByBrandAndColor(@RequestBody Car car, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "price"));
        return carElasticRepository.findByBrandAndColor(car.getBrand(), car.getColor(), pageable).getContent();
    }

    @GetMapping("/cars/{brand}/{color}")
    public ResponseEntity<Object> getCarsByPath(@PathVariable("brand") String brand, @PathVariable("color") String color
            , @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SERVER, "Spring");
        httpHeaders.add("X-Custom-Header", "Custom Response Header");
        if(StringUtils.isNumeric(color)) {
            ErrorResponse errorResponse = new ErrorResponse("Invalid color : " + color, LocalDateTime.now());
            return new ResponseEntity<>(errorResponse, httpHeaders, HttpStatus.BAD_REQUEST);
        }
        Pageable pageable = PageRequest.of(page, size);
        List<Car> cars =  carElasticRepository.findByBrandAndColor(brand, color, pageable).getContent();
        return ResponseEntity.ok().headers(httpHeaders).body(cars);
    }

    @GetMapping("/cars")
    public List<Car> findCarsByParam(@RequestParam("brand") String brand,@RequestParam("color") String color
            , @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        if(StringUtils.isNumeric(color)) {
            throw new IllegalArgumentException("Invalid color : " + color);
        }
        Pageable pageable = PageRequest.of(page, size);
        return carElasticRepository.findByBrandAndColor(brand, color, pageable).getContent();
    }

    @GetMapping("/cars/date") // Specifies what the input date should look like.
    public List<Car> findCarsReleasedAfter(@RequestParam("release_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate releaseDate) {
        return carElasticRepository.findByReleaseDate(releaseDate);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        String message = "Exception, " + ex.getMessage();
        logger.warn(message);
        ErrorResponse errorResponse = new ErrorResponse(message, LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
