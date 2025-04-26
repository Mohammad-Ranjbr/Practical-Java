package com.example.practical.repository;

import com.example.practical.entity.Car;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car, String> {

    List<Car> findByBrandAndColor(String brand, String color);
    List<Car> findByReleaseDateAfter(LocalDate releaseDateAfter);

}
