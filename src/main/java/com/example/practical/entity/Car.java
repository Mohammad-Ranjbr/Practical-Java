package com.example.practical.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "practical-java")
// In camelCase, the first letter is lowercase and each subsequent word starts with an uppercase letter. All lowercase, words are separated by underscores.
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Car {

    // In Spring Data Elasticsearch (and in general in Elasticsearch itself) we must have a unique identifier (ID) for each document (i.e. each record of our data).
    // In Elasticsearch, each document (such as an object of the Car class) must be stored in an Index and must have a unique ID so that:
    // it can be identified, operations such as update, delete or get can be performed
    // In order for Spring to know which field has the ID role, one of the fields must be marked with @Id.

    @Id
    private String id;
    private String brand;
    private String color;
    private String type;
    private int price;
    private boolean available;
    @Field(type = FieldType.Date, format = DateFormat.date)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Tehran")
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
