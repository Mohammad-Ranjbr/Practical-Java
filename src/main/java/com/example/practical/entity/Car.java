package com.example.practical.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private String brand;
    private String color;
    private String type;
    private int price;
    private boolean available;
    private LocalDate releaseDate;
    private List<String> additionalFeature;
    private Engine engine;
    private List<Tire> tires;

}
