package com.example.practical.service.impl;

import com.example.practical.entity.Car;
import com.example.practical.entity.Engine;
import com.example.practical.entity.Tire;
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
        LocalDate releaseDate = RandomDateGenerator.generateDate();

        int randomCount = ThreadLocalRandom.current().nextInt(ADDITIONAL_FEATURE.size());
        List<String> additionalFeature = new ArrayList<>();
        for(int i=0; i<randomCount; i++){
            additionalFeature.add(ADDITIONAL_FEATURE.get(i));
        }

        String fuel = FUELS.get(ThreadLocalRandom.current().nextInt(0, FUELS.size()));
        int horsePower = ThreadLocalRandom.current().nextInt(100,250);
        Engine engine = new Engine(horsePower, fuel);

        List<Tire> tires = new ArrayList<>();
        for(int i=0; i<4; i++){
            String manufacturer = TIRE_MANUFACTURER.get(ThreadLocalRandom.current().nextInt(0, TIRE_MANUFACTURER.size()));
            int tierPrice = ThreadLocalRandom.current().nextInt(500, 700);
            int size = ThreadLocalRandom.current().nextInt(15, 20);
            Tire tire = new Tire(size, tierPrice, manufacturer);
            tires.add(tire);
        }

        return new Car(brand, color, type, price, available, releaseDate, additionalFeature, engine, tires);
    }

}
