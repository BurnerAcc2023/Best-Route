package com.lucidity.BestRoute.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lucidity.BestRoute.entity.Restaurant;
import com.lucidity.BestRoute.exception.NotFoundException;
import com.lucidity.BestRoute.service.RestaurantService;
import com.lucidity.BestRoute.util.FileReaderUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private List<Restaurant> restaurantList;

    @PostConstruct
    public void init() {
        TypeReference<List<Restaurant>> typeReference = new TypeReference<>() {};
        restaurantList = FileReaderUtils.read("ExampleRestaurantData.json",typeReference);
    }
    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantList.stream()
                .filter(restaurant -> restaurant.getRestaurantId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Restaurant not found"));
    }
}
