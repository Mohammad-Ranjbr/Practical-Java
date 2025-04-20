package com.example.practical.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
// In camelCase, the first letter is lowercase and each subsequent word starts with an uppercase letter. All lowercase, words are separated by underscores.
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Car {

    private String brand;
    private String color;
    private String type;
    private int price;
    private boolean available;
    @JsonFormat(pattern = "dd-MMM-yyyy", timezone = "Asia/Tehran")
    private LocalDate releaseDate;
    private List<String> additionalFeature;
    // This annotation tells Jackson to only include fields in the JSON output that are: not null and not empty, such as: strings " - lists [] - maps {}
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String secretFeature;
    // Allows you to unpack the fields of an internal object directly into JSON (instead of being in a nested object).
    @JsonUnwrapped
    private Engine engine;
    private List<Tire> tires;

}
