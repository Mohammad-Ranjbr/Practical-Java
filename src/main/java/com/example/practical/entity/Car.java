package com.example.practical.entity;

import lombok.*;

import java.time.LocalDate;

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

}
