package com.lucidity.BestRoute.service;

import com.lucidity.BestRoute.entity.Restaurant;
import org.mapstruct.Named;

public interface RestaurantService {
    @Named("getRestaurant")
    Restaurant getRestaurantById(Long id);
}
