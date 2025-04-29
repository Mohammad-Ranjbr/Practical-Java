package com.example.practical.api.server;

import com.example.practical.entity.CarPromotion;
import com.example.practical.exception.IllegalApiParamException;
import com.example.practical.repository.CarPromotionElasticRepository;
import com.example.practical.service.CarPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/car/v1")
public class CarPromotionApi {

    private final CarPromotionService carPromotionService;
    private final CarPromotionElasticRepository carPromotionElasticRepository;

    @Autowired
    public CarPromotionApi(CarPromotionService carPromotionService, CarPromotionElasticRepository carPromotionElasticRepository) {
        this.carPromotionService = carPromotionService;
        this.carPromotionElasticRepository = carPromotionElasticRepository;
    }

    @GetMapping("/promotions")
    public List<CarPromotion> listAvailablePromotions(@RequestParam("type") String promotionType,
                                                      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        if(!carPromotionService.isValidPromotionType(promotionType)){
            throw new IllegalApiParamException("Invalid promotion type : " + promotionType);
        }
        Pageable pageable = PageRequest.of(page, size);
        return carPromotionElasticRepository.findByType(promotionType, pageable).getContent();
    }

}
