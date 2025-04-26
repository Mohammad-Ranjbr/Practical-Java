package com.example.practical.common;

import com.example.practical.entity.Car;
import com.example.practical.repository.CarElasticRepository;
import com.example.practical.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarElasticDatasource {

    private final WebClient webClient;
    private final CarService carService;
    private final CarElasticRepository carElasticRepository;
    private static final Logger logger = LoggerFactory.getLogger(CarElasticDatasource.class);

    @Autowired
    public CarElasticDatasource(@Qualifier("webClientElasticsearch") WebClient webClient,
                                @Qualifier("randomCarService") CarService carService, CarElasticRepository carElasticRepository) {
        this.webClient = webClient;
        this.carService = carService;
        this.carElasticRepository = carElasticRepository;
    }

    // This annotation causes the method it is applied to be executed when the Spring Boot application is fully loaded (and ready to run).
    // This means that the populateData() method is executed only once after the application is loaded.
    // .retrieve()
    // This method says "get the response result". After the DELETE request has been executed, we now want to read the response.
    // .block()
    // This method makes the program wait for the response to come and then return the response value.
    // In fact, with .block(), the program runs synchronously and waits for the result to come back.
    //@EventListener(ApplicationReadyEvent.class)
    public void populateData(){
        var response = webClient.delete().uri("/practical-java").retrieve().bodyToMono(String.class).block();
        logger.info("End delete with response {}", response);

        List<Car> cars = new ArrayList<>();
        for (int i=0 ; i<10_0000 ; i++) {
            cars.add(carService.generateCar(String.valueOf(i + 1)));
        }
        carElasticRepository.saveAll(cars);
        logger.info("Saved {} cars", carElasticRepository.count());
    }

}
