package com.example.practical.repository;

import com.example.practical.entity.Car;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car, String> {
}
