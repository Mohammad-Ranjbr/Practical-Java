package com.example.practical.service;

import com.example.practical.entity.Car;

import java.util.List;

public interface CarService {

    List<String> BRANDS = List.of("Toyota", "Honda", "Ford");
    List<String> COLORS = List.of("Red", "Blue", "Yellow", "Green");
    List<String> TYPES = List.of("Sedan", "SUV", "MPV");
    List<Integer> PRICES = List.of(500_000, 1_000_000, 2_000_000, 3_000_000);
    List<String> ADDITIONAL_FEATURE = List.of("GPS", "Alarm", "Sunroof", "Media Player", "Leather Seats");
    List<String> FUELS = List.of("Petrol", "Electric", "Hybrid");
    List<String> TIRE_MANUFACTURER = List.of("Goodyear", "Bridgestone", "Dunlop");

    Car generateCar();

}
