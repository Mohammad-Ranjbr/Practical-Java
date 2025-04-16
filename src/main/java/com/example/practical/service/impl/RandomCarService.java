package com.example.practical.service.impl;

import com.example.practical.entity.Car;
import com.example.practical.service.CarService;
import com.example.practical.util.RandomDateGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomCarService implements CarService {

    @Override
    public Car generateCar() {
        String brand = BRANDS.get(ThreadLocalRandom.current().nextInt(0, BRANDS.size()));
        String color = COLORS.get(ThreadLocalRandom.current().nextInt(0, COLORS.size()));
        String type = TYPES.get(ThreadLocalRandom.current().nextInt(0, TYPES.size()));
        int price = PRICES.get(ThreadLocalRandom.current().nextInt(0, PRICES.size()));
        boolean available = ThreadLocalRandom.current().nextBoolean();

        int randomCount = ThreadLocalRandom.current().nextInt(ADDITIONAL_FEATURE.size());
        List<String> additionalFeature = new ArrayList<>();
        for(int i=0; i<randomCount; i++){
            additionalFeature.add(ADDITIONAL_FEATURE.get(i));
        }

        LocalDate releaseDate = RandomDateGenerator.generateDate();

        return new Car(brand, color, type, price, available, releaseDate, additionalFeature);
    }

}
