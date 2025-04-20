package com.example.practical.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
// The @JsonIgnoreProperties annotation is placed on the class. This is another Jackson feature that works very similarly to @JsonIgnore,
// but instead of applying it to just one field, you can use it to ignore multiple fields at once — very useful when you don't want to show multiple fields in JSON.
@JsonIgnoreProperties(value = {"color", "serialNumber"})
public class Engine {

    private int horsePower;
    private String fuelType;
    private String color;
    private String serialNumber;

}
