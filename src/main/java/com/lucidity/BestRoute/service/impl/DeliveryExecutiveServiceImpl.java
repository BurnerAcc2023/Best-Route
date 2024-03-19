package com.lucidity.BestRoute.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lucidity.BestRoute.entity.DeliveryExecutive;
import com.lucidity.BestRoute.exception.NotFoundException;
import com.lucidity.BestRoute.service.DeliveryExecutiveService;
import com.lucidity.BestRoute.util.FileReaderUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeliveryExecutiveServiceImpl implements DeliveryExecutiveService {
    private List<DeliveryExecutive> deliveryExecutiveList;

    @PostConstruct
    public void init() {
        TypeReference<List<DeliveryExecutive>> typeReference = new TypeReference<>() {};
        deliveryExecutiveList = FileReaderUtils.read("ExampleDeliveryExecutiveData.json",typeReference);
    }
    @Override
    public DeliveryExecutive getDeliveryExecutiveById(Long id) {
        return deliveryExecutiveList.stream()
                .filter(deliveryExecutive -> deliveryExecutive.getDeliveryExecutiveId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Delivery Executive Not Found"));
    }
}
