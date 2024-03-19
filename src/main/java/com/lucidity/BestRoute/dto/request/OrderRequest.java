package com.lucidity.BestRoute.dto.request;

import com.lucidity.BestRoute.entity.OrderItem;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequest {
    private Long restaurantId;
    private Long customerId;
    private Integer preparationTime;
    private List<OrderItem> orderItems;
    private Double totalOrderValue;
    private Long deliveryExecutiveId;
}
