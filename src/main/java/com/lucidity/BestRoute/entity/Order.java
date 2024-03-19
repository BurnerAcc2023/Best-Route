package com.lucidity.BestRoute.entity;

import com.lucidity.BestRoute.entity.enums.OrderStatus;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class Order {
    private Long orderId;
    private Customer customer;
    private Restaurant restaurant;
    private OrderStatus orderStatus;
    private Instant orderPlacedAt;
    private List<OrderItem> orderItems;
    private Double totalOrderValue;
    private Integer preparationTime;
    private DeliveryExecutive deliveryExecutive;
}
