package com.lucidity.BestRoute.service;

import com.lucidity.BestRoute.entity.DeliveryExecutive;
import org.mapstruct.Named;

public interface DeliveryExecutiveService {
    @Named("getDeliveryExecutive")
    DeliveryExecutive getDeliveryExecutiveById(Long Id);
}
