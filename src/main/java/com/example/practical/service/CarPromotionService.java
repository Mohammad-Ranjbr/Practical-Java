package com.example.practical.service;

import java.util.List;

public interface CarPromotionService {

    List<String> PROMOTION_TYPE = List.of("bonus", "discount");
    boolean isValidPromotionType(String promotionType);

}
