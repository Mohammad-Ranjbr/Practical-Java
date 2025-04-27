package com.example.practical.repository;

import com.example.practical.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car, String> {

    Page<Car> findByBrandAndColor(String brand, String color, Pageable pageable);
    List<Car> findByReleaseDate(LocalDate releaseDateAfter);

}
