package com.example.practical.service;

import com.example.practical.entity.Car;

import java.util.List;

public interface CarService {

    List<String> BRANDS = List.of("Toyota", "Honda", "Ford");
    List<String> COLORS = List.of("Red", "Blue", "Yellow", "Green");
    List<String> TYPES = List.of("Sedan", "SUV", "MPV");

    Car generateCar();

}
