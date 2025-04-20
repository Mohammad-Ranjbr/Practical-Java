package com.example.practical.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Tire {

    @JsonProperty(value = "diameter")
    private int size;
    @JsonIgnore // It causes Jackson to ignore that particular field when converting a Java object to JSON (and vice versa).
    private int price;
    private String manufacturer;

}
