package com.example.practical.service.impl;

import com.example.practical.service.CarPromotionService;
import org.springframework.stereotype.Service;

@Service
public class DefaultCarPromotionService implements CarPromotionService {

    @Override
    public boolean isValidPromotionType(String promotionType) {
        return PROMOTION_TYPE.contains(promotionType.toLowerCase());
    }

}
