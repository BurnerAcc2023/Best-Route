package com.lucidity.BestRoute.mapper;

import com.lucidity.BestRoute.dto.request.OrderRequest;
import com.lucidity.BestRoute.entity.Order;
import com.lucidity.BestRoute.service.CustomerService;
import com.lucidity.BestRoute.service.DeliveryExecutiveService;
import com.lucidity.BestRoute.service.RestaurantService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerService.class, RestaurantService.class, DeliveryExecutiveService.class})
public interface OrderMapper {

    @Mapping(target = "customer", source = "customerId", qualifiedByName = "getCustomer")
    @Mapping(target = "restaurant", source = "restaurantId", qualifiedByName = "getRestaurant")
    @Mapping(target = "deliveryExecutive", source = "deliveryExecutiveId", qualifiedByName = "getDeliveryExecutive")
    Order convert(OrderRequest request);
}
