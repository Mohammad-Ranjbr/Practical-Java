package com.example.practical.common;

import com.example.practical.entity.CarPromotion;
import com.example.practical.repository.CarPromotionElasticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarPromotionElasticDatasource {

    private final CarPromotionElasticRepository carPromotionElasticRepository;
    private static final Logger logger = LoggerFactory.getLogger(CarPromotionElasticDatasource.class);

    @Autowired
    public CarPromotionElasticDatasource(CarPromotionElasticRepository carPromotionElasticRepository) {
        this.carPromotionElasticRepository = carPromotionElasticRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void populateData(){
        carPromotionElasticRepository.deleteAll();
        List<CarPromotion> carPromotions = getCarPromotions();
        carPromotionElasticRepository.saveAll(carPromotions);
        logger.info("Saved dummy promotion data : {}", carPromotionElasticRepository.count());

    }

    private static List<CarPromotion> getCarPromotions() {
        CarPromotion carPromotion1 = new CarPromotion("1", "bonus", "Purchase in cash get free luxury dinner");
        CarPromotion carPromotion2 = new CarPromotion("2", "bonus", "Purchase in cash get free luxury Japan");
        CarPromotion carPromotion3 = new CarPromotion("3", "bonus", "Purchase two cars and get 20 gram of gold");
        CarPromotion carPromotion4 = new CarPromotion("4", "discount", "Purchase in cash and 1% discount");
        CarPromotion carPromotion5 = new CarPromotion("5", "discount", "Purchase before and of month to get 1.5% discount");
        CarPromotion carPromotion6 = new CarPromotion("6", "discount", "Today only! We gives 0.5% additional discount");
        return List.of(carPromotion1, carPromotion2, carPromotion3, carPromotion4, carPromotion5, carPromotion6);
    }

}
